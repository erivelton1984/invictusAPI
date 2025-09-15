package br.com.invictus.services;

import br.com.invictus.data.vo.UserVO;
import br.com.invictus.data.vo.security.TokenVO;
import br.com.invictus.model.UserModel;
import br.com.invictus.repositories.UserRepository;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PasswordRecoveryService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void sendRecoveryEmail(String username, String email) throws Exception {

        UserModel user = userRepository.findByUserNameOrEmail(username, email);

        if (user == null) {
            throw new Exception("Usuário não encontrado.");
        }

        // Gera um token de redefinição e uma data de expiração (por exemplo, 1 hora)
        String resetPasswordToken = UUID.randomUUID().toString();
        Date resetPasswordExpiration = new Date(System.currentTimeMillis() + 3600000); // 1 hora

        // Cria o TokenVO com o token de redefinição
        TokenVO tokenVO = new TokenVO();
        tokenVO.setResetPasswordToken(resetPasswordToken);
        tokenVO.setResetPasswordExpiration(resetPasswordExpiration);

        // Envia o e-mail com o token
        String recoveryLink = generateRecoveryLink(resetPasswordToken);
        sendEmail(user.getEmail(), recoveryLink);
    }

    private void sendEmail(String email, String recoveryLink) {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("erivelton.r.pinto@gmail.com", "rugj htvc kqzh xfhw");
            }
        });

        session.setDebug(true);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("erivelton.r.pinto@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Recuperação de Senha");
            message.setText("Olá,\n\nPara redefinir sua senha, clique no link abaixo:\n" + recoveryLink);

            Transport.send(message);
            System.out.println("E-mail de recuperação enviado com sucesso para: " + email);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao enviar e-mail: " + e.getMessage());
        }
    }

    public boolean newPassword(UserVO userVO) {

        var user = userRepository.findByUserName(userVO.getUserName());

        if (user == null) {
            throw new RuntimeException("Usuário não encontrado!");
        }

        try {

            Map<String, PasswordEncoder> encoders = new HashMap<>();
            Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder(
                    "", 8, 185000, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
            encoders.put("pbkdf2", pbkdf2Encoder);

            DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
            passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

            String passwordEncrypted = passwordEncoder.encode(userVO.getPassword());

            // Remove o prefixo "{pbkdf2}" se necessário
            if (passwordEncrypted.startsWith("{pbkdf2}")) {
                passwordEncrypted = passwordEncrypted.substring("{pbkdf2}".length());
            }

            // Atualiza a senha do usuário
            user.setPassword(passwordEncrypted);

            // Salva a nova senha
            userRepository.save(user);
            return true;

        } catch (Exception e) {
            // Log do erro
            System.err.println("Erro ao redefinir a senha: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private String generateRecoveryLink(String resetPasswordToken) {
        return "http://3.144.221.193:3000/invictus/redefinirSenha";
    }
}