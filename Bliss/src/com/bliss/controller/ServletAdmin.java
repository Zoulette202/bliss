package com.bliss.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.bliss.dao.AdministrateurDAO;
import com.bliss.metier.Administrateur;

@WebServlet("/admin/*")
public class ServletAdmin extends UtilHttpServlet {
	private static final long serialVersionUID = 1L;  
	
	public static final String CHAMP_NOM = "lastname"; 
	public static final String CHAMP_PRENOM = "firstname"; 
	public static final String CHAMP_EMAIL = "mail"; 
	public static final String CHAMP_PASS = "password"; 
//	public static final String CHAMP_CONF = "password_confirmation"; 
	public static final String ATT_ERREURS  = "erreurs";
    public static final String ATT_RESULTAT = "resultat";
	
	public void create() throws ServletException, IOException{
        String resultat;
        Map<String, String> erreurs = new HashMap<String, String>();
		if(this.req.getMethod().equals("POST")) {
	        /* Récupération des champs du formulaire. */
			String lastname = req.getParameter("lastname"); 
			String firstname = req.getParameter("firstname");
			String mail = req.getParameter("mail");
			String password = req.getParameter("password");
			String password_confirmation = req.getParameter("password_confirmation");
			
			try {
				validationNom(lastname);

			} catch (Exception e) {
	            erreurs.put(CHAMP_NOM, e.getMessage());
			}
			
            try {
				validationPrenom(firstname);
			} catch (Exception e) {
	            erreurs.put(CHAMP_PRENOM, e.getMessage());
			}
            
            try {
				validationEmail(mail);
			} catch (Exception e) {
	            erreurs.put(CHAMP_EMAIL, e.getMessage());
			}
            
            try {
				validationMotsDePasse(password, password_confirmation);
			} catch (Exception e) {
	            erreurs.put(CHAMP_PASS, e.getMessage());
			}
            
            /* Initialisation du résultat global de la validation. */
            if ( erreurs.isEmpty() ) {
                resultat = "Succès de la création.";
                Administrateur a = new Administrateur(); 
    			a.setLastname(lastname);
    			a.setFirstname(firstname);
    			a.setMail(mail);
    			a.setPassword(password);
    			AdministrateurDAO.save(a); 
    			
            } else {
                resultat = "Échec de la création.";
            }
            
            /* Stockage du résultat et des messages d'erreur dans l'objet request */
            req.setAttribute( ATT_ERREURS, erreurs );
            req.setAttribute( ATT_RESULTAT, resultat );
	
			this.displayView(null);

		}else 
			this.displayView(null);
    }
	
	/**
	 * Valide le nom d'utilisateur saisi.
	 */
	private void validationNom( String nom ) throws Exception {
	    if ( nom != null && nom.trim().length() < 3 ) {
	        throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
	    }
	}
	
	/**
	 * Valide le prenom d'utilisateur saisi.
	 */
	private void validationPrenom( String nom ) throws Exception {
	    if ( nom != null && nom.trim().length() < 3 ) {
	        throw new Exception( "Le nom d'utilisateur doit contenir au moins 3 caractères." );
	    }
	}
	
	/**
	 * Valide l'adresse mail saisie.
	 */
	private void validationEmail( String email ) throws Exception {
	    if ( email != null && email.trim().length() != 0 ) {
	        if ( !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) {
	            throw new Exception( "Merci de saisir une adresse mail valide." );
	        }
	    } else {
	        throw new Exception( "Merci de saisir une adresse mail." );
	    }
	}
	
	/**
	 * Valide les mots de passe saisis.
	 */
	private void validationMotsDePasse( String motDePasse, String confirmation ) throws Exception{
	    if (motDePasse != null && motDePasse.trim().length() != 0 && confirmation != null && confirmation.trim().length() != 0) {
	        if (!motDePasse.equals(confirmation)) {
	            throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
	        } else if (motDePasse.trim().length() < 3) {
	            throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
	        }
	    } else {
	        throw new Exception("Merci de saisir et confirmer votre mot de passe.");
	    }
	}
}