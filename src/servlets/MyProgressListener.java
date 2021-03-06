package servlets;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.ProgressListener;
public class MyProgressListener implements ProgressListener {
    private HttpSession session;
    public MyProgressListener(HttpServletRequest request){
        session = request.getSession();
    }
    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        //�����ݽ��и�ʽ��
        //�Ѷ�ȡ�������ֽ�ת��ΪM
        double readM=pBytesRead/1024.0/1024.0;
        //�Ѷ�ȡ�������ֽ�ת��ΪM
        double totalM=pContentLength/1024.0/1024.0;
        //�Ѷ�ȡ�ٷְ�
        double percent=readM/totalM;
        
        //��ʽ������
        //�Ѷ�ȡ
        String readf=dataFormat(pBytesRead);
        //�ܴ�С
        String totalf=dataFormat(pContentLength);
        //���Ȱٷְ�
        NumberFormat format=NumberFormat.getPercentInstance();
        String progress=format.format(percent);
        
        //����Ϣ����session
        session.setAttribute("progress", progress);

    }
    /**
     * ��ʽ����ȡ���ݵ���ʾ
     * @param dataҪ��ʽ�������� ��λbyte
     * @return ��ʽ��������ݣ����С��1M��ʾ��λΪKB���������1M��ʾ��λΪM
     */
    public String dataFormat(double data){
        String formdata="";
        if (data>=1024*1024) {//���ڵ���1M
            formdata=Double.toString(data/1024/1024)+"M";
        }else if(data>=1024){//���ڵ���1KB
            formdata=Double.toString(data/1024)+"KB";
        }else{//С��1KB
            formdata=Double.toString(data)+"byte";
        }
        return formdata.substring(0, formdata.indexOf(".")+2);
    }

}