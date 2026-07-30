package classes;

import com.db4o.Db4o;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

/*
@author Rafael C. M. Marques
*/

public class LivroDAL {

    private static ObjectContainer db;
        
    public static void conecta(String _bd)
    {
        try 
        {
            db = Db4o.openFile(_bd);
            Erro.setErro(false);
        } 
        catch (Exception e) 
        {
            Erro.setErro("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    public static void desconecta()
    {
        if (db != null) 
        {
            db.close();
            db = null;
        }
    }

    public static void inseriLivro(Livro umlivro)
    {
        try 
        {
            db.set(umlivro);
            Erro.setErro(false);
        } 
        catch (Exception e) 
        {
            Erro.setErro("Erro ao inserir o livro: " + e.getMessage());
        }
    }

    public static void consultaLivro(Livro umlivro)
    {
        try 
        {
            Livro x = new Livro();
            x.setTitulo(umlivro.getTitulo().trim());

            ObjectSet resultado = db.get(x);

            if (resultado.hasNext()) {
                Livro encontrado = (Livro) resultado.next();
                umlivro.setAutor(encontrado.getAutor());
                umlivro.setEditora(encontrado.getEditora());
                umlivro.setAnoEdicao(encontrado.getAnoEdicao());
                umlivro.setLocalizacao(encontrado.getLocalizacao());
                Erro.setErro(false);
            } else {
                Erro.setErro("Livro não encontrado!");
            }
        } 
        catch (Exception e) 
        {
            Erro.setErro("Erro ao consultar o livro: " + e.getMessage());
        }
    }
    
    public static void excluiLivro(Livro umlivro)
    {
        try 
        {
            Livro x = new Livro();
            x.setTitulo(umlivro.getTitulo().trim());

            ObjectSet resultado = db.get(x);

            if (resultado.hasNext()) 
            {
                Livro encontrado = (Livro) resultado.next();
                db.delete(encontrado);
                Erro.setErro(false);
            } 
            else 
            {
                Erro.setErro("Livro não encontrado para exclusão!");
            }
        } 
        catch (Exception e) 
        {
            Erro.setErro("Erro ao excluir o livro: " + e.getMessage());
        }
    }

    public static void alteraLivro(Livro umlivro)
    {
        try 
        {
            Livro x = new Livro();
            x.setTitulo(umlivro.getTitulo().trim());

            ObjectSet resultado = db.get(x);

            if (resultado.hasNext()) {
                Livro encontrado = (Livro) resultado.next();
                encontrado.setAutor(umlivro.getAutor());
                encontrado.setEditora(umlivro.getEditora());
                encontrado.setAnoEdicao(umlivro.getAnoEdicao());
                encontrado.setLocalizacao(umlivro.getLocalizacao());
                
                db.set(encontrado);
                Erro.setErro(false);
            } 
            else 
            {
                Erro.setErro("Livro não encontrado para alteração!");
            }
        } 
        catch (Exception e) 
        {
            Erro.setErro("Erro ao alterar o livro: " + e.getMessage());
        }
    }
}
