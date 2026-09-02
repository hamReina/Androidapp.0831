package jp.ac.meijou.android.s251205043;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Optional;

import jp.ac.meijou.android.s251205043.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.buttonA.setOnClickListener(View->{
            var intent =new Intent(this,MainActivity3.class);
            startActivity(intent);
        });

        binding.buttonB.setOnClickListener(View->{
            var inset=new Intent();
            inset.setAction(Intent.ACTION_VIEW);
            inset.setData(Uri.parse("https://www.yahoo.co.jp"));
            startActivity(inset);
        });

        binding.intentButton.setOnClickListener(view->{
            String sentText=binding.intentEditText.getText().toString();

            var intent = new Intent(this,MainActivity3.class);
            intent.putExtra("editText",sentText);
            startActivity(intent);
        });

        binding.buttonUp.setOnClickListener(view->{
            var intent =new Intent(this,MainActivity3.class);
            getActivityResult.launch(intent);
        });
    }

    //Activity
    private final ActivityResultLauncher<Intent> getActivityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                switch (result.getResultCode()) {
                    case RESULT_OK -> {
                        Optional.ofNullable(result.getData())
                                .map(data -> data.getStringExtra("ret"))
                                .map(text -> "Result: " + text)
                                .ifPresent(text -> binding.intentResult.setText(text));
                    }
                    case RESULT_CANCELED -> {
                        binding.intentResult.setText("Result: Canceled");
                    }
                    default -> {
                        binding.intentResult.setText("Result: Unknown(" + result.getResultCode() + ")");
                    }
                }
            }
    );
}