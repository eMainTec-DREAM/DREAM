package dream.part.list.service.spring;

import common.bean.User;
import dream.part.list.dao.PartListSafQtyDetailDAO;
import dream.part.list.dto.PartListSafQtyDetailDTO;
import dream.part.list.dto.PartListSafQtyListDTO;
import dream.part.list.service.PartListSafQtyDetailService;

/**
 * 부품창고 보관위치 - Detail Service implements
 * @author cjscjs9
 * @version $Id:$
 * @since 1.0
 * @spring.bean id="partListSafQtyDetailServiceTarget"
 * @spring.txbn id="partListSafQtyDetailService"
 * @spring.property name="partListSafQtyDetailDAO" ref="partListSafQtyDetailDAO"
 */
public class PartListSafQtyDetailServiceImpl implements PartListSafQtyDetailService
{
    private PartListSafQtyDetailDAO partListSafQtyDetailDAO = null;
    
    public PartListSafQtyDetailDTO findPtWhEmpDetail(PartListSafQtyListDTO partListSafQtyListDTO, User user) throws Exception
    {
    	return partListSafQtyDetailDAO.findPtWhEmpDetail(partListSafQtyListDTO, user);
    }
    
    public int insertPtWhEmpDetail(PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user) throws Exception
    {
    	return partListSafQtyDetailDAO.insertPtWhEmpDetail(partListSafQtyDetailDTO, user);
    }
    
    public int updatePtWhEmpDetail(PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user) throws Exception
    {
    	 return partListSafQtyDetailDAO.updatePtWhEmpDetail(partListSafQtyDetailDTO, user);
    }

	public PartListSafQtyDetailDAO getPartListSafQtyDetailDAO() {
		return partListSafQtyDetailDAO;
	}

	public void setPartListSafQtyDetailDAO(PartListSafQtyDetailDAO partListSafQtyDetailDAO) {
		this.partListSafQtyDetailDAO = partListSafQtyDetailDAO;
	}

	public String validEmpNo(PartListSafQtyListDTO partListSafQtyListDTO, PartListSafQtyDetailDTO partListSafQtyDetailDTO, User user) throws Exception
	{
        return partListSafQtyDetailDAO.validEmpNo(partListSafQtyListDTO, partListSafQtyDetailDTO, user);
	}
}
