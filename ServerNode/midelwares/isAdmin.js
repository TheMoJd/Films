// middlewares/isAdmin.js
const isAdmin = (req, res, next) => {
    if (req.user && req.user.isAdmin) {
        next();
    } else {
        res.status(403).send({ message: "Accès réservé aux administrateurs." });
    }
};

module.exports = isAdmin;
