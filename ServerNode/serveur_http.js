require('dotenv').config();
const express = require('express');
const jwt = require('jsonwebtoken');
const config = require("./config.js");
const bd = require('./mysql2');
const cors = require('cors');
const bcrypt = require('bcryptjs');
const verifyToken = require('./midelwares/verifyToken.js');
const isAdmin = require('./midelwares/isAdmin.js');

const app = express();
app.use(cors());
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

const PRIVATE_KEY = "zjerYhe+7V"; // À déplacer dans un fichier de configuration ou une variable d'environnement

app.post("/connexion", async (req, res) => {
    const { username, password } = req.body;
    try {
        const result = await bd.connexion(username, password);
        if (result.success) {
            const token = jwt.sign(result.user, PRIVATE_KEY, { expiresIn: '1h' });
            res.send({ res: true, token: token });
        } else {
            res.status(401).send({ res: false, mess: [result.message] });
        }
    } catch (error) {
        res.status(500).send({ res: false, mess: ["Erreur serveur"] });
    }
});

app.post("/inscription", async (req, res) => {
    const { username, password } = req.body;
    try {
        //const hashedPassword = await bcrypt.hash(password, 10); // ca marche passsssssssssssssssss
        const result = await bd.inscription(username, password);
        if (result.success) {
            res.send({ res: true, mess: "Inscription réussie !" });
        } else {
            res.status(400).send({ res: false, mess: [result.message] });
        }
    } catch (error) {
        res.status(500).send({ res: false, mess: ["Erreur serveur"] });
    }
});

// Exemple d'utilisation pour une route accessible par tous les utilisateurs authentifiés
app.get("/profile", verifyToken, (req, res) => {
    res.send("Profil de l'utilisateur");
});

// Exemple d'utilisation pour une route réservée aux administrateurs
app.get("/admin", verifyToken, isAdmin, (req, res) => {
    res.send("Zone Admin");
});

const port = process.env.PORT || 3000;
app.listen(port, () => {
    console.log(`Serveur lancé sur http://localhost:${port}`);
});