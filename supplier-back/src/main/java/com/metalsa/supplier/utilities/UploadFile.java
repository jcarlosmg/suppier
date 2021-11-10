package com.metalsa.supplier.utilities;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;

import lombok.extern.log4j.Log4j;

@Log4j
public class UploadFile {
    
    //ruta RAIZ del FTP de compras
    public static final String FTP_PATH_ROOT_DCOM = "/compras01/ftpcomp";
    //ruta para archivos de cotizaciones
    public static final String FTP_PATH_COT_DCOM = "/test/nvcompras/cotizaciones/";
    //ruta para archivos de catalogos
    public static final String FTP_PATH_CAT_DCOM = "/test/nvcompras/catalogos/";
    //public static final String FTP_PATH_COT_DCOM = "/test/nvcompras/cotizaciones/";
    public static final String FTP_PATH_RFQ_DCOM = "/test/nvcompras/rfq";

    public static final String FTP_PATH_DEFAULT = "/dev/2303/bdg_increase/";

    public static boolean connect(FTPClient client, String host, Integer port, String user, String pass) {
        boolean success = false;
        log.debug("host: " + host);
        try {
            client.connect(host, port);
            log.debug("conectando........");
            success = client.login(user, pass);
        } catch (IOException ex) {
            log.debug(ex.getMessage());
        }
        return success;
    }

    public static boolean upload(InputStream file, String path, String fileName, String host, Integer port, String user, String pass) {
        log.debug("inicianado........");
        FTPClient client = new FTPClient();
        boolean respuesta = false;

        try {
            boolean success = connect(client, host, port, user, pass);

            if (success) {
                log.debug("logueado........");
            }
            client.setFileType(FTPClient.BINARY_FILE_TYPE);
            client.enterLocalPassiveMode();

            String remoto = path + fileName;

            //
            // Store file to server
            //
            log.debug("remoto:" + remoto);
            log.debug("fileName:" + fileName);
            log.debug("file:" + file);

            if (!client.changeWorkingDirectory(path)) {
                log.info("No existe directorio, creando " + path);
                createDirectoryTreeFTP(client, path);
            }

            respuesta = client.storeFile(fileName, file);
            if (respuesta) {
                log.debug("guardado........" + path);
            }
            client.logout();
            log.debug("desloguenado........");
        } catch (IOException e) {
            log.debug(e);
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
                client.disconnect();
                log.debug("desconectando........");
            } catch (IOException e) {
                log.debug(e);
            }
        }
        log.debug("respuesta: " + respuesta);
        return respuesta;
    }

    
    public static void createDirectoryTreeFTP(FTPClient client, String dirTree) throws IOException {
        log.debug("*** createDirectoryTreeFTP ***");
        boolean dirExists = true;
        String[] directories = dirTree.split("/");
        for (String dir : directories) {
            if (!dir.isEmpty()) {
                if (dirExists) {
                    dirExists = client.changeWorkingDirectory(dir);
                }
                if (!dirExists) {
                    if (!client.makeDirectory(dir)) {
                        throw new IOException("Unable to create remote directory '" + dir + "'.  error=" + client.getReplyString() + "'");
                    }
                    if (!client.changeWorkingDirectory(dir)) {
                        throw new IOException("Unable to change into newly created remote directory '" + dir + "'.  error='" + client.getReplyString() + "'");
                    }
                }
            }
        }
    }
}

