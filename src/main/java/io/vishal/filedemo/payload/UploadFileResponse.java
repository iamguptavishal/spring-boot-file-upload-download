package io.vishal.filedemo.payload;

public class UploadFileResponse {
    private String fileName;
    private String fileType;
    private long size;
    private String fileDownloadUrl;

    public UploadFileResponse(String fileName, String fileType, long size, String fileDownloadUrl) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
        this.fileDownloadUrl = fileDownloadUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFileDownloadUrl() {
        return fileDownloadUrl;
    }

    public void setFileDownloadUrl(String fileDownloadUrl) {
        this.fileDownloadUrl = fileDownloadUrl;
    }
}
