package dream.part.iss.wo.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.iss.wo.dao.PartIssWoPartListDAO;
import dream.part.iss.wo.dto.PartIssWoPartListDTO;
import dream.part.iss.wo.service.PartIssWoPartListService;

/**
 * 자재출고WO - 목록 serviceimpl
 * @author ghlee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partIssWoPartListServiceTarget"
 * @spring.txbn id="partIssWoPartListService"
 * @spring.property name="partIssWoPartListDAO" ref="partIssWoPartListDAO"
 */
public class PartIssWoPartListServiceImpl implements PartIssWoPartListService
{
    private PartIssWoPartListDAO partIssWoPartListDAO = null;
    
    public PartIssWoPartListDAO getPartIssWoPartListDAO()
    {
        return partIssWoPartListDAO;
    }

    public void setPartIssWoPartListDAO(PartIssWoPartListDAO partIssWoPartListDAO)
    {
        this.partIssWoPartListDAO = partIssWoPartListDAO;
    }

    public List findWoPartList(PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception
    {      
        return partIssWoPartListDAO.findWoPartList(partIssWoPartListDTO,user);
    }

    @Override
    public int deleteWoPartList(String[] deleteRows, User user) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null)){
            for(String woPartId : deleteRows) {
                result = result + partIssWoPartListDAO.deleteWoPart(woPartId, user);
            }
        }
        
        return result;
    }
    
    @Override
    public String findWoPartTotalCount(PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception
    {
        return partIssWoPartListDAO.findWoPartTotalCount(partIssWoPartListDTO,user);
    }

    @Override
    public int linkWoPartList(PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception
    {
        int result = 0;
        
        String[] woPartId = partIssWoPartListDTO.getMultiWoPartId().split("\\+");
        
        for(int i=0; woPartId.length > i; i++)
        {
            result = result + partIssWoPartListDAO.linkWoPartDetail(woPartId[i], partIssWoPartListDTO, user);
        }
        
        return result;
    }
    
    @Override
    public int addWoPartList(PartIssWoPartListDTO partIssWoPartListDTO, User user) throws Exception
    {
        int result = 0;
        
        String[] wkOrId = partIssWoPartListDTO.getMultiWkOrId().split("\\+");
        
        for(int i=0; wkOrId.length > i; i++)
        {
            result = result + partIssWoPartListDAO.addWoPartDetail(wkOrId[i], partIssWoPartListDTO, user);
        }
        
        return result;
    }
}

