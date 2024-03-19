const fs = require('fs')

const base = "C:\\Users\\jaoue\\Desktop\\S8\\Films\\ServerNode\\serveur_http.js" // !!!!! À MODIFIER !!!!!!
const port_http = 3000

const mysql2 = {
    host: "localhost",
    user: "root",
    password: "",
    database: "films",

    // pour pool de connexions
    waitForConnections: true,
    connectionLimit: 10,
    queueLimit: 0
}


if (!fs.existsSync(base)) {
    console.log("Erreur chargement config.js")
    console.log("Le dossier "+base+" n'existe pas")
    console.log("Modifier la variable base")
    process.exit(0)
}


module.exports = {
    base : base,
    port_http : port_http,
    mysql2 : mysql2
}
