package entidades;

public class RegraIgualitaria extends CalculoDivida {

    Republica republica = new Republica();

    //public float divIgual(){
    //    return rendaTotal(republica.getListPessoas());
    //}

    public float divIgual(){
        return despesaTotal(republica.getListaDespesas())/republica.getListPessoas().size();
    }
    

    

}
