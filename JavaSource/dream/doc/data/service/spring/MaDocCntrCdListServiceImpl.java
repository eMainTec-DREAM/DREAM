package dream.doc.data.service.spring;

import java.util.List;

import common.bean.User;
import dream.doc.data.dao.MaDocCntrCdListDAO;
import dream.doc.data.dto.MaDocCntrCdCommonDTO;
import dream.doc.data.dto.MaDocCntrCdListDTO;
import dream.doc.data.service.MaDocCntrCdListService;

/**
 * �Ϲ��ڷ�� - ��� serviceimpl
 * @author ssong
 * @version
 * @since 1.0
 * 
 * @spring.bean id="maDocCntrCdListServiceTarget"
 * @spring.txbn id="maDocCntrCdListService"
 * @spring.property name="maDocCntrCdListDAO" ref="maDocCntrCdListDAO"
 */
public class MaDocCntrCdListServiceImpl implements MaDocCntrCdListService
{
    private MaDocCntrCdListDAO maDocCntrCdListDAO = null;

    public MaDocCntrCdListDAO getMaDocCntrCdListDAO() 
    {
		return maDocCntrCdListDAO;
	}

	public void setMaDocCntrCdListDAO(MaDocCntrCdListDAO maDocCntrCdListDAO) 
	{
		this.maDocCntrCdListDAO = maDocCntrCdListDAO;
	}

	public List findList(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User loginUser)
    {      
        return maDocCntrCdListDAO.findList(maDocCntrCdCommonDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
        {
        	MaDocCntrCdListDTO maDocCntrCdListDTO = null;
            for(String id : deleteRows)
            {
            	maDocCntrCdListDTO = new MaDocCntrCdListDTO();
            	maDocCntrCdListDTO.setDocCntrId(id);
                // ÷������ ���纰 �ŷ�ó ������ �ڷ� ���� 
                result = result + maDocCntrCdListDAO.deleteObjDoc(maDocCntrCdListDTO, loginUser);
                result = result + maDocCntrCdListDAO.deleteDocCntr(maDocCntrCdListDTO, loginUser);
            }
        }
        return result;
    }

	@Override
	public String findTotalCount(MaDocCntrCdCommonDTO maDocCntrCdCommonDTO, User user)
	{
        return maDocCntrCdListDAO.findTotalCount(maDocCntrCdCommonDTO, user);
	}
}

