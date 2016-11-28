package larissa.modulocompra;

import android.content.Context;
import android.util.Log;

import java.io.FileOutputStream;

/**
 * Created by Larissa on 28/11/2016.
 */
public class ManageFile {
    private static final String TAG = "ManageFile";
    private Context context;

    public ManageFile(Context context){
        this.context = context;
    }

    public boolean WriteFile(String text){
        try {
            // Abre o arquivo para escrita ou cria se não existir
            FileOutputStream out = context.openFileOutput("NotaFiscal.txt",
                    Context.MODE_PRIVATE);
            out.write(text.getBytes());
            out.write("\n".getBytes());
            out.flush();
            out.close();
            return true;

        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

}
