package aplicacao;

import dominio.DAO;
import dominio.Pessoa;

public class Programa {
    public static void main(String[] args) {
        Pessoa p1 = new Pessoa(null ,"Kadu Gabriel Marques de França","kadugabrielmdf@gmail.com");
        Pessoa p2 = new Pessoa(null ,"Maria da Silva Beneguete","MariaS@gmail.com");
        Pessoa p3 = new Pessoa(null ,"Mario Luiz Afonseca","MarioL@gmail.com");
        DAO<Pessoa> daoPessoa = new DAO<>(Pessoa.class);

        daoPessoa.abrir()
               .create(p1)
               .create(p2)
               .create(p3)
               .fechar();

        System.out.println(daoPessoa.encontrarPeloId(3));
        System.out.println("Nome da pessoa: " + daoPessoa.encontrarPeloId(2).getNome());
        System.out.println(daoPessoa.encontrarPeloId(1));
        System.out.println(daoPessoa.atualizar(1, "Kadu França", "kadu@hotmail.com"));
        System.out.println(daoPessoa.encontrarPeloId(1));



    }

}
