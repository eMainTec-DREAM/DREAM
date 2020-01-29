package intf.dream.android.offline.madoclib.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import org.apache.struts.upload.FormFile;


/**
 * 첨부파일 - 목록 service
 * @author  kim21017
 * @version $Id: AnIfDocLibListService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface AnIfDocLibListService
{
    public int uploadFiles(Map map,FormFile[] fileList) throws FileNotFoundException, IOException;

}
