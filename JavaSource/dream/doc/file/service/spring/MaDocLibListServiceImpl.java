package dream.doc.file.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.file.FileUploadUtil;
import common.finder.valid.dao.ValidationDAO;
import dream.doc.file.dao.MaDocLibListDAO;
import dream.doc.file.dto.MaDocLibCommonDTO;
import dream.doc.file.service.MaDocLibListService;


/**
 * ÷������ - ��� serviceimpl
 * @author jung7126
 * @version $Id: MaDocLibListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maDocLibListServiceTarget"
 * @spring.txbn id="maDocLibListService"
 * @spring.property name="maDocLibListDAO" ref="maDocLibListDAO"
 * @spring.property name="validationDAO" ref="validationDAO"
 */
public class MaDocLibListServiceImpl implements MaDocLibListService
{
    private MaDocLibListDAO maDocLibListDAO = null;

    private ValidationDAO validationDAO = null;
    
    
    public ValidationDAO getValidationDAO()
    {
        return validationDAO;
    }

    public void setValidationDAO(ValidationDAO validationDAO)
    {
        this.validationDAO = validationDAO;
    }

    public MaDocLibListDAO getMaDocLibListDAO() {
		return maDocLibListDAO;
	}

	public void setMaDocLibListDAO(MaDocLibListDAO maDocLibListDAO) {
		this.maDocLibListDAO = maDocLibListDAO;
	}

	public List findList(MaDocLibCommonDTO maDocLibCommonDTO,User user)
    {      
	    String objectType = maDocLibCommonDTO.getObjectType();
	    if(!"".equals(objectType) && objectType != null) {
	        
	    }
        return maDocLibListDAO.findList(maDocLibCommonDTO, user);
    }
	
	public int deleteList(String[] deleteRows, MaDocLibCommonDTO maDocLibCommonDTO, User user) throws Exception{
        int result = 0;
        
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                //÷�ι��� �޴����� ������...
                if(maDocLibCommonDTO.getObjectId() == "")
                {
                    result = result + maDocLibListDAO.deleteDoc(id, user);   //�����з� (����) ����
                    List fileList = validationDAO.findFileAttach(id, "", user); //File ����
                    for(int i = 0; fileList.size() > i ; i++)
                    {
                        Map map = (Map)fileList.get(i);
                        
                        String docdataId = String.valueOf(map.get("DOCDATA_ID"));
                        String nfPath = String.valueOf(map.get("NFFILEPATH"));
                        
                        FileUploadUtil.deleteFile(nfPath, docdataId);
                    }
                    result = result + maDocLibListDAO.deleteDocData(id, user); //�������� (÷������) ���� ����
                    result = result + maDocLibListDAO.deleteObjDoc(id, "", "", user);  //������������ (��ũ) ����
                }
                else
                {
                    result = result + maDocLibListDAO.deleteObjDoc(id, maDocLibCommonDTO.getObjectId(), maDocLibCommonDTO.getObjectType(), user);  //������������ (��ũ)
                }
            }
        
        return result;
    }

	
	public void linkList(MaDocLibCommonDTO maDocLibCommonDTO, User user) {
		
		maDocLibCommonDTO.setCompNo(user.getCompNo());
		maDocLibListDAO.linkDoc(maDocLibCommonDTO);
		
	}
	
	public String findTotalCount(MaDocLibCommonDTO maDocLibCommonDTO,User user)
    {
        return maDocLibListDAO.findTotalCount(maDocLibCommonDTO, user);
    }
}

