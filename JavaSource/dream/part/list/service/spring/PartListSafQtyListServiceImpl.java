package dream.part.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.list.dao.PartListSafQtyListDAO;
import dream.part.list.dto.PartListSafQtyListDTO;
import dream.part.list.service.PartListSafQtyListService;

/**
 * 부품창고 보관위치 - List Service implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="partListSafQtyListServiceTarget"
 * @spring.txbn id="partListSafQtyListService"
 * @spring.property name="partListSafQtyListDAO" ref="partListSafQtyListDAO"
 */
public class PartListSafQtyListServiceImpl implements PartListSafQtyListService
{
	private PartListSafQtyListDAO partListSafQtyListDAO = null;

	public List findPtWhEmpList(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception
    {      
        return partListSafQtyListDAO.findPtWhEmpList(partListSafQtyListDTO,user);
    }

	public int deletePtWhEmpList( String[] deleteRows, User user) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + partListSafQtyListDAO.deletePtWhEmpList(id, user);
            }
        return result;
    }

	public PartListSafQtyListDAO getPartListSafQtyListDAO() {
		return partListSafQtyListDAO;
	}

	public void setPartListSafQtyListDAO(PartListSafQtyListDAO partListSafQtyListDAO) {
		this.partListSafQtyListDAO = partListSafQtyListDAO;
	}
	public String findTotalCount(PartListSafQtyListDTO partListSafQtyListDTO,User user) throws Exception
    {
        return partListSafQtyListDAO.findTotalCount(partListSafQtyListDTO, user);
    }
}

