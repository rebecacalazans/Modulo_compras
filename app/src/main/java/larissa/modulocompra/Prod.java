package larissa.modulocompra;

/**
 * Created by Larissa on 20/11/2016.
 */
public class Prod {
    private int idProduto;
    private String mercadoProduto;
    private String tipoProduto;
    private String nomeProduto;
    private double precoProduto;
    private int quantidadeProduto;

    public Prod() {
        quantidadeProduto = 0;
    }

    public Prod(int id, String mercado,String tipo, String nome, double preco) {
        idProduto = id;
        mercadoProduto = mercado;
        tipoProduto = tipo;
        nomeProduto = nome;
        precoProduto = preco;
        quantidadeProduto = 0;
    }
    public int getId() { return idProduto; }
    public String getTipoProduto() { return tipoProduto; }
    public String getMercadoProduto() { return mercadoProduto; }
    public String getNomeProduto() { return nomeProduto; }
    public double getPrecoProduto() { return precoProduto; }
    public double getPrecoTotal(){return quantidadeProduto*precoProduto;}
    public int getQuantidadeProduto() { return quantidadeProduto; }
    public void setQuantidadeProduto(int quantidadeProduto) {  this.quantidadeProduto = quantidadeProduto; }
    public void setId(int id){this.idProduto = id;}
    public void setMercadoProduto(String mercadoProduto) { this.mercadoProduto = mercadoProduto; }
    public void setTipoProduto(String tipoProduto) { this.tipoProduto = tipoProduto; }
    public void setNomeProduto(String nomeProduto) { this.nomeProduto = nomeProduto; }
    public void setPrecoProduto(double precoProduto) { this.precoProduto = precoProduto; }

    @Override
    public String toString() {
        return tipoProduto + " - " + nomeProduto + " - " + precoProduto;
    }
}
