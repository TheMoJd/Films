const mysql2 = require('mysql2/promise');
const bcrypt = require('bcryptjs');
const config_mysql2 = require('./config.js').mysql2;

const pool = mysql2.createPool(config_mysql2);


async function connexion(username, password) {
    const sql = 'SELECT * FROM users WHERE username = ?';
    console.log(username, password);
    try {
        const [rows] = await pool.query(sql, [username]); // rows est un tableau de lignes  qui correspondent à la requête (SELECT * FROM users WHERE username = ?)
        console.log(rows);
        if (rows.length > 0) {
            const user = rows[0];
            console.log("Password (plain text):", password);
            console.log("Password (from DB, hashed):", user.password);

            //const passwordIsValid = await bcrypt.compare(password, user.password); CA MARCHE PASsSSSSSSSSSS
            const passwordIsValid = password === user.password;

            console.log("Password is valid:", passwordIsValid);
            if (passwordIsValid) {
                return { success: true, user };
            }
        }
        return { success: false, message: "Identifiants incorrects" };
    } catch (err) {
        console.error("Erreur lors de la connexion :", err);
        return { success: false, message: "Erreur lors de la connexion" };
    }
}

async function inscription(username, password) {
    const sqlVerification = 'SELECT * FROM users WHERE username = ?';
    try {
        const [rows] = await pool.query(sqlVerification, [username]);
        if (rows.length > 0) {
            return { success: false, message: "Nom d'utilisateur déjà utilisé" };
        }
        //const hash = await bcrypt.hash(password, 10);
        const sql = 'INSERT INTO users (username, password) VALUES (?, ?)';
        await pool.query(sql, [username, password]);
        return { success: true };
    } catch (err) {
        console.error("Erreur lors de l'inscription :", err);
        return { success: false, message: "Erreur lors de l'inscription" };
    }
}

//test de cryptage de mot de passe
/*async function testBcrypt() {
    const password = 'mo';
    const saltRounds = 8;

    const hashedPassword = await bcrypt.hash(password, saltRounds);
    console.log("Hashed Password:", hashedPassword);

    const match = await bcrypt.compare(password, hashedPassword);
    console.log("Password match:", match);
}

testBcrypt();*/
module.exports = { connexion, inscription };
