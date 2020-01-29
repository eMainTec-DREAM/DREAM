package dream.org.dept.service.spring;

import common.bean.User;
import dream.org.dept.dao.MaDeptDetailDAO;
import dream.org.dept.dto.MaDeptDetailDTO;
import dream.org.dept.service.MaDeptDetailService;

/**
 * 보전부서 - 상세 serviceimpl 
 * @author  
 * @version $Id:  $
 * @since   1.0
 * @spring.bean id="maDeptDetailServiceTarget"
 * @spring.txbn id="maDeptDetailService"
 * @spring.property name="maDeptDetailDAO" ref="maDeptDetailDAO"
 */
public class MaDeptDetailServiceImpl implements MaDeptDetailService
{
    private MaDeptDetailDAO maDeptDetailDAO = null;
    
    public MaDeptDetailDAO getMaDeptDetailDAO() {
		return maDeptDetailDAO;
	}

	public void setMaDeptDetailDAO(MaDeptDetailDAO maDeptDetailDAO) {
		this.maDeptDetailDAO = maDeptDetailDAO;
	}

	public MaDeptDetailDTO findDetail(User user, String deptNo)throws Exception
    {
        return maDeptDetailDAO.findDetail(user, deptNo);
    }
    
	public int insertDetail(MaDeptDetailDTO maDeptDetailDTO) throws Exception
    {        
        return maDeptDetailDAO.insertDetail(maDeptDetailDTO);
    }
	
	public int updateDetail(MaDeptDetailDTO maDeptDetailDTO) throws Exception
    {        
        return maDeptDetailDAO.updateDetail(maDeptDetailDTO);
    }
	
	public String validDeptNo(MaDeptDetailDTO maDeptDetailDTO) throws Exception
    {
        return maDeptDetailDAO.validDeptNo(maDeptDetailDTO);
    }
}
