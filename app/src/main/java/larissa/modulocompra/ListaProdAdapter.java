package larissa.modulocompra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Larissa on 26/11/2016.
 */
public class ListaProdAdapter extends ArrayAdapter<Prod> {

    private Context context;
    private List<Prod> produtos = null;

    public ListaProdAdapter(Context context, List<Prod> pessoas ) {
        super(context, 0, pessoas);

        this.produtos = pessoas;
        this.context = context;
    }//fecha construtor

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Prod p = produtos.get(position);

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.listado, null);
        }

        TextView texto_esquerdo_entrada = (TextView) convertView.findViewById(R.id.textView_esquerda);
        texto_esquerdo_entrada.setText(p.getNomeProduto());

        TextView texto_centro_entrada = (TextView) convertView.findViewById(R.id.textView_centro);
        texto_centro_entrada.setText(String.valueOf(p.getQuantidadeProduto()));

        TextView texto_direita_entrada = (TextView) convertView.findViewById(R.id.textView_direita);
        texto_direita_entrada.setText(String.format("%.2f",p.getPrecoTotal()));

        return convertView;
    }//fecha getView
}//fecha classe
