// middlewares/verifyToken.js
const jwt = require('jsonwebtoken');

const verifyToken = (req, res, next) => {
    let token = req.headers['x-access-token'] || req.headers['authorization'];

    if (!token) {
        return res.status(403).send({ message: "Aucun token fourni." });
    }

    if (token.startsWith('Bearer ')) {
        // Supprimez 'Bearer ' du token
        token = token.slice(7, token.length);
    }

    jwt.verify(token, process.env.PRIVATE_KEY, (err, decoded) => {
        if (err) {
            return res.status(401).send({ message: "Non autorisé." });
        }
        req.user = decoded;
        next();
    });
};

module.exports = verifyToken;
