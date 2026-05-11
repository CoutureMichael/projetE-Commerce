package com.ecommerce.seeder;

import com.ecommerce.model.Produit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class produitSeeder {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ecommercePU");
        EntityManager em = emf.createEntityManager();

        try {
            // Vérifier que le catalogue n'est pas déjà seedé
            long count = (long) em.createQuery("SELECT COUNT(p) FROM Produit p").getSingleResult();
            if (count == 0) {
                em.getTransaction().begin();

                Produit p1 = new Produit(
                        "Clavier mécanique Corsair K95 RGB",
                        "Clavier gaming mécanique rétroéclairé RGB, switches Cherry MX, repose-poignets.",
                        199.99,
                        "https://th.bing.com/th/id/OIP.7unSxT3-qAva624EapHMLgHaD9?w=325&h=180&c=7&r=0&o=7&pid=1.7&rm=3",
                        "Accessoires gaming"
                );
                Produit p2 = new Produit(
                        "Souris Logitech G502 Hero",
                        "Souris filaire gaming, capteur HERO 25K DPI, 11 boutons programmables.",
                        89.99,
                        "https://th.bing.com/th/id/OIP.Qn-7U6DQsa5EZ-8_LYaP5AHaFj?w=223&h=180&c=7&r=0&o=7&pid=1.7&rm=3",
                        "Accessoires gaming"
                );
                Produit p3 = new Produit(
                        "Écran Samsung Odyssey G7 32”",
                        "Écran incurvé QHD 32 pouces, 240Hz, 1ms, compatible G-Sync/FreeSync.",
                        749.99,
                        "https://images.samsung.com/is/image/samsung/p6pim/fr/ls32bg700euxen/gallery/fr-odyssey-g7-g70b-ls32bg700euxen-542670443?$650_519_PNG$",
                        "Moniteurs"
                );
                Produit p4 = new Produit(
                        "PC Portable MSI Raider GE76",
                        "PC portable gaming 17”, Intel i9, RTX 3080, 32Go RAM, 1To SSD.",
                        3299.00,
                        "https://img.gamingstore.com/msi-ge76.jpg",
                        "Ordinateurs portables"
                );
                Produit p5 = new Produit(
                        "Casque SteelSeries Arctis Pro",
                        "Casque micro gaming Hi-Res, son surround, sans fil, autonomie 20h.",
                        249.00,
                        "https://th.bing.com/th/id/OIP.9-N2XEhDl0T-X49OJ5SRUgHaKb?w=136&h=192&c=7&r=0&o=7&pid=1.7&rm=3",
                        "Audio"
                );
                Produit p6 = new Produit(
                        "Chaise gaming DXRacer Formula",
                        "Fauteuil ergonomique pour gamer, support lombaire, inclinaison 135°.",
                        329.90,
                        "https://www.maxgaming.com/bilder/magic360/32124/FORMULA-L-D23-LTC-NR-X1-001.jpg",
                        "Mobilier gaming"
                );
                Produit p7 = new Produit(
                        "Carte graphique NVIDIA RTX 4080",
                        "GPU dernière génération, 16Go GDDR6X, Ray Tracing, HDMI/DP.",
                        1349.99,
                        "https://media.ldlc.com/r1600/ld/products/00/05/99/55/LD0005995555.jpg",
                        "Composants PC"
                );
                Produit p8 = new Produit(
                        "Manette Xbox Elite Series 2",
                        "Manette sans fil pro, sticks interchangeables, pour PC/Xbox.",
                        179.95,
                        "https://th.bing.com/th/id/OIP.tlA5ea2t--TsuJLlxLf7UAHaHa?w=182&h=182&c=7&r=0&o=7&pid=1.7&rm=3",
                        "Accessoires gaming"
                );

                em.persist(p1);
                em.persist(p2);
                em.persist(p3);
                em.persist(p4);
                em.persist(p5);
                em.persist(p6);
                em.persist(p7);
                em.persist(p8);

                em.getTransaction().commit();
                System.out.println("Produits gaming créés !");
            } else {
                System.out.println("Des produits existent déjà, seed ignoré.");
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}