package ru.stqa.pft.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {

    private final ApplicationManager app;
    private FTPClient ftp;

    public FtpHelper(ApplicationManager app) {
        this.app = app;
        ftp = new FTPClient();
    }

    public void upload(File file, String target, String backup) throws IOException { //загружает новый файл,а старый переименовывает
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(backup); //удаление прошлой копии
        ftp.rename(target, backup); // переменование файла
        ftp.enterLocalPassiveMode(); //пассивный режим передачи данных
        ftp.storeFile(target, new FileInputStream(file)); //передача файла
        ftp.disconnect(); //разрыв соединения
    }

    public void restore(String backup, String target) throws IOException { //востанавливает старый файл
        ftp.connect(app.getProperty("ftp.host"));
        ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
        ftp.deleteFile(backup);
        ftp.rename(target, backup);
        ftp.disconnect();
    }

}
