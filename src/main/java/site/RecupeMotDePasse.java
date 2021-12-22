package site;

import beans.Utilisateur;
import dao.DaoFactory;
import dao.UtilisateurDao;
import utils.AppUtils;
import utils.TokenGeneration;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@WebServlet(name = "recupeMotDePasse", value = "/recupeMotDePasse")
public class RecupeMotDePasse extends HttpServlet {
    private UtilisateurDao utilisateurDao;

    @Override
    public void init() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/recupeMotDePasse.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        //verification remplissage formulaire
        if (email != "" && email != null ) {
            if(utilisateurDao.emailUtilisateurExiste(email)){
                Utilisateur utilisateur = utilisateurDao.getUtilisateurParEmail(email);

                String token = TokenGeneration.generateNewToken();
                utilisateurDao.enregistrementDemandeRecupMotDePasse(utilisateur, token);

                //Envoie email
                Properties props = new Properties();
                props.put("mail.smtp.host", "127.0.0.1");
                props.put("mail.smtp.port", "25");
                props.put("mail.debug", "true");
                Session session = Session.getDefaultInstance(props);
                try
                {
                    MimeMessage msg = new MimeMessage(session);
                    msg.setFrom();
                    msg.setRecipients(Message.RecipientType.TO,
                            utilisateur.getEmail());
                    msg.setSubject("Changement de mot de passe sur Covid19.com", "text/html; charset=utf-8");
                    msg.setSentDate(new Date());
                    msg.setText("\n" +
                            "         <html>\n" +
                            "          <head>\n" +
                            "           <title>Réinitialisation de votre mot de passe Covid19</title>\n" +
                            "          </head>\n" +
                            "          <body>\n" +
                            "           <p>Nous avons reçu une demande de réinitialisation de mot de passe pour votre compte.</p>\n" +
                            "           <p>Veuillez confirmer la réinitialisation pour choisir un nouveau mot de passe.</p>\n" +
                            "           <p>Autrement, vous pouvez ignorer cet e-mail.</p>\n" +
                            "           <a href=\"" + request.getContextPath() + "/nouveauMotDePasse&token=" + token + "\">Confirmer la réinitialisation de votre mot de passe</a>\n" +
                            "          </body>\n" +
                            "         </html>\n" +
                            "      ", "text/html; charset=utf-8");
                    Transport.send(msg);
                }
                catch (MessagingException mex)
                {
                    mex.printStackTrace();
                    String erreur = "Une erreur s'est produite lors de l'envoie du mail. Veuillez contacter un administrateur";
                    request.setAttribute("erreur", erreur);
                    request.setAttribute("messageDemandeAccepte", "");
                    request.setAttribute("email", email);
                    this.getServletContext().getRequestDispatcher("/WEB-INF/recupeMotDePasse.jsp").forward(request, response);
                    return;
                }

                String messageDemandeAccepte = " Un email a été envoyé à  " + email + " afin de récupérer votre mot de passe.\n" +
                        "Veuillez vous connecter à votre boite mail où vous trouverez un email contenant un lien.\n" +
                        "    Cliquez sur ce lien pour créer un nouveau mot de passe.\n" +
                        "    (Si vous ne trouvez pas le mail, il est peut-être dans vos spam)";
                request.setAttribute("erreur", "");
                request.setAttribute("messageDemandeAccepte", messageDemandeAccepte);
                request.setAttribute("email", email);
                this.getServletContext().getRequestDispatcher("/WEB-INF/recupeMotDePasse.jsp").forward(request, response);
            }
            else {
                String erreur = "Aucun compte ne correspond à cette adresse email. Veuillez contacter un administrateur";
                request.setAttribute("erreur", erreur);
                request.setAttribute("messageDemandeAccepte", "");
                request.setAttribute("email", email);
                this.getServletContext().getRequestDispatcher("/WEB-INF/recupeMotDePasse.jsp").forward(request, response);
            }
        }
        else {
            String erreur = "Informations rentrées incorrectes";
            request.setAttribute("erreur", erreur);
            request.setAttribute("messageDemandeAccepte", "");
            request.setAttribute("email", email);
            this.getServletContext().getRequestDispatcher("/WEB-INF/recupeMotDePasse.jsp").forward(request, response);
        }
    }

}