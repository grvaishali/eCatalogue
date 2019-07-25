package listener;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ButtonListener {
    ImageButton viewImage;

    ButtonListener() {
        ButtonListener object = new ButtonListener();
    }

    public static void dismiss(ImageButton button, final Button dismiss) {
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss.setVisibility( View.GONE );
            }
        } );

    }


}
