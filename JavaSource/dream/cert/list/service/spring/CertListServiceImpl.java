package dream.cert.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.cert.list.dao.CertDetailDAO;
import dream.cert.list.dao.CertListDAO;
import dream.cert.list.dto.CertCommonDTO;
import dream.cert.list.service.CertListService;

/**
 * 자격증분류 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="certListServiceTarget"
 * @spring.txbn id="certListService"
 * @spring.property name="certListDAO" ref="certListDAO"
 * @spring.property name="certDetailDAO" ref="certDetailDAO"
 */
public class CertListServiceImpl implements CertListService
{
    private CertListDAO certListDAO = null;
    private CertDetailDAO certDetailDAO = null;

    public CertDetailDAO getCertDetailDAO()
    {
        return certDetailDAO;
    }

    public void setCertDetailDAO(CertDetailDAO certDetailDAO)
    {
        this.certDetailDAO = certDetailDAO;
    }

    public CertListDAO getCertListDAO() 
    {
        return certListDAO;
    }

    public void setCertListDAO(CertListDAO certListDAO) 
    {
        this.certListDAO = certListDAO;
    }

    public List findList(CertCommonDTO certCommonDTO, User user)
    {      
        return certListDAO.findList(certCommonDTO,user);
    }
    
    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;
        String certListId = "";
        
        for(int i = 0; deleteRows.length > i ; i++)
        {
        	certListId = deleteRows[i];


                result = result + certListDAO.deleteList(compNo, certListId);

        }
        
        return result;
    }

    @Override
    public String findTotalCount(CertCommonDTO certCommonDTO, User user) throws Exception
    {
        return certListDAO.findTotalCount(certCommonDTO, user);
    }

}