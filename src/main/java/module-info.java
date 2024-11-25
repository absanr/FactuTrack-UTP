module com.absanr.factutrack {
    // Requiere acceso a módulos importantes
    requires java.desktop; // Incluye AWT, Swing y datatransfer
    requires java.sql; // Acceso a la base de datos
    requires io.github.cdimascio.dotenv.java; // Biblioteca dotenv-java para la configuración de entorno
    requires java.logging;
    requires AbsoluteLayout.RELEASE230;

    // Exporta paquetes principales
    exports com.absanr.factutrack;
    exports com.absanr.factutrack.controller;
    exports com.absanr.factutrack.model; // Exporta el paquete de modelos (si se necesita fuera)
    exports com.absanr.factutrack.dao; // Exporta el paquete DAO (si se necesita fuera)

    // Abre paquetes para la reflexión, si es necesario
    opens com.absanr.factutrack.controller to java.desktop; // Permite acceso reflexivo a los controladores
}
