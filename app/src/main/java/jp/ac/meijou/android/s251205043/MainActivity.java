package jp.ac.meijou.android.s251205043;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import jp.ac.meijou.android.s251205043.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PrefDataStore prefDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        prefDataStore = PrefDataStore.getInstance(this);
        //prefDataStore.getString("name")
                //.ifPresent(name -> binding.textView.setText(name));

        prefDataStore.getString("name")
                .ifPresent(text ->{
                    if("a".equals(text)){
                        binding.textView.setText("Aの画像");
                        binding.imageView.setImageResource(R.drawable.ic_add_home);
                    }else if("b".equals(text)){
                        binding.textView.setText("Bの画像");
                        binding.imageView.setImageResource(R.drawable.ic_add_location);
                    }else{
                        binding.textView.setText("知らない画像");
                    }
                });

        //TextView textView = findViewById(R.id.text_view);
        //textView.setText(R.string.app_name);
        //binding.textView.setText(R.string.app_name);

        binding.button.setOnClickListener(view -> {
            String text = binding.editTextText.getText().toString();


            binding.textView.setText(text);
        });

        binding.savebutton.setOnClickListener(view -> {
            var text = binding.editTextText.getText().toString();
            prefDataStore.setString("name", text);

            if("a".equals(text)){
                binding.imageView.setImageResource(R.drawable.ic_android);
            }else if("b".equals(text)){
                binding.imageView.setImageResource(R.drawable.ic_add_location);
            }else{
                text="unknouwn";
            }
        });

        binding.editTextText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                String text = editable.toString();
                binding.textView.setText(text);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });
    }
}