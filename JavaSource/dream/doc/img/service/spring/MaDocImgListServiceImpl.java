package dream.doc.img.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.file.FileUploadUtil;
import common.finder.valid.dao.ValidationDAO;
import dream.doc.img.dao.MaDocImgListDAO;
import dream.doc.img.dto.MaDocImgCommonDTO;
import dream.doc.img.service.MaDocImgListService;


/**
 * 사진파일 - 목록 serviceimpl
 * @author jung7126
 * @version $Id: MaDocImgListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maDocImgListServiceTarget"
 * @spring.txbn id="maDocImgListService"
 * @spring.property name="maDocImgListDAO" ref="maDocImgListDAO"
 * @spring.property name="validationDAO" ref="validationDAO"
 */
public class MaDocImgListServiceImpl implements MaDocImgListService
{
    private MaDocImgListDAO maDocImgListDAO = null;

    private ValidationDAO validationDAO = null;
    
    
    public ValidationDAO getValidationDAO()
    {
        return validationDAO;
    }

    public void setValidationDAO(ValidationDAO validationDAO)
    {
        this.validationDAO = validationDAO;
    }

    public MaDocImgListDAO getMaDocImgListDAO() {
		return maDocImgListDAO;
	}

	public void setMaDocImgListDAO(MaDocImgListDAO maDocImgListDAO) {
		this.maDocImgListDAO = maDocImgListDAO;
	}

	public List findList(MaDocImgCommonDTO maDocImgCommonDTO, User user)
    {      
        return maDocImgListDAO.findList(maDocImgCommonDTO, user);
    }
	
	public int deleteList(String[] deleteRows, MaDocImgCommonDTO maDocImgCommonDTO, User user) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {

                result = result + maDocImgListDAO.deleteDoc(id);   //문서분류 (문서) 삭제
                List fileList = validationDAO.findImageAttach(id, "", user); //File 삭제
                for(int i = 0; fileList.size() > i ; i++)
                {
                    Map map = (Map)fileList.get(i);
                    
                    String docdataId = String.valueOf(map.get("IMGDATA_ID"));
                    String nfPath = String.valueOf(map.get("NFFILEPATH"));
                    
                    FileUploadUtil.deleteFile(nfPath, docdataId);
                }
                
                result = result + maDocImgListDAO.deleteDocData(id); //문서파일 (첨부파일) 정보 삭제

            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaDocImgCommonDTO maDocImgCommonDTO, User user)
	{
        return maDocImgListDAO.findTotalCount(maDocImgCommonDTO, user);
	}
}

