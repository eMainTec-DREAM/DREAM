package dream.consult.comp.user.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.comp.user.dao.ConsultCompUserListDAO;
import dream.consult.comp.user.dto.ConsultCompUserCommonDTO;
import dream.consult.comp.user.service.ConsultCompUserListService;

/**
 * CompUser Page - List Service implements
 * @author youngjoo38
 * @version $Id: ConsultCompUserListServiceImpl.java,v 1.0 2017/08/10 09:12:40 youngjoo38 Exp $
 * @since 1.0
 * @spring.bean id="consultCompUserListServiceTarget"
 * @spring.txbn id="consultCompUserListService"
 * @spring.property name="consultCompUserListDAO" ref="consultCompUserListDAO"
 */
public class ConsultCompUserListServiceImpl implements ConsultCompUserListService
{
    private ConsultCompUserListDAO consultCompUserListDAO = null;

    public List findCompUserList(ConsultCompUserCommonDTO consultCompUserCommonDTO, User user) throws Exception
    {      
        return consultCompUserListDAO.findCompUserList(consultCompUserCommonDTO,user);
    }

    public int deleteCompUserList( String[] deleteRows, User user, ConsultCompUserCommonDTO consultCompUserCommonDTO) throws Exception
    {
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + consultCompUserListDAO.deleteCompUserList(id, user, consultCompUserCommonDTO);
            }
        return result;
    }

    public ConsultCompUserListDAO getConsultCompUserListDAO() {
        return consultCompUserListDAO;
    }

    public void setConsultCompUserListDAO(ConsultCompUserListDAO consultCompUserListDAO) {
        this.consultCompUserListDAO = consultCompUserListDAO;
    }    
    
    public String findTotalCount(ConsultCompUserCommonDTO consultCompUserCommonDTO,User user)  throws Exception
    {
        return consultCompUserListDAO.findTotalCount(consultCompUserCommonDTO, user);
    }
    
    public int resetCompUserPw( String[] resetRows, String[] userNo, String[] compNo) throws Exception
    {
        int result = 0;

        if(!resetRows.equals(null))
            for(int i=0;i<resetRows.length;i++)
            {
                result = result + consultCompUserListDAO.resetCompUserPw(resetRows[i], userNo[i], compNo[i]);
            }
        return result;
    }
}
