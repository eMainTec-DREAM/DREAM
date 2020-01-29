package dream.part.list.service.spring;

import common.bean.User;
import dream.part.list.dao.MaPtMstrVendorDetailDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrVendorDetailDTO;
import dream.part.list.service.MaPtMstrVendorDetailService;

/**
 * ��ǰ�ŷ�ó
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtMstrVendorDetailServiceTarget"
 * @spring.txbn id="maPtMstrVendorDetailService"
 * @spring.property name="maPtMstrVendorDetailDAO" ref="maPtMstrVendorDetailDAO"
 */
public class MaPtMstrVendorDetailServiceImpl implements MaPtMstrVendorDetailService
{
    private MaPtMstrVendorDetailDAO maPtMstrVendorDetailDAO = null;
    
    public MaPtMstrVendorDetailDAO getMaPtMstrVendorDetailDAO() 
    {
		return maPtMstrVendorDetailDAO;
	}

	public void setMaPtMstrVendorDetailDAO(MaPtMstrVendorDetailDAO maPtMstrVendorDetailDAO) 
	{
		this.maPtMstrVendorDetailDAO = maPtMstrVendorDetailDAO;
	}

	public MaPtMstrVendorDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)throws Exception
    {
        return maPtMstrVendorDetailDAO.findDetail(maPtMstrCommonDTO, loginUser);
    }
    
	public int updateDetail(MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO, User loginUser) throws Exception
    {        
        return maPtMstrVendorDetailDAO.updateVendor(maPtMstrVendorDetailDTO, loginUser);
    }
	
	public int insertDetail(MaPtMstrVendorDetailDTO maPtMstrVendorDetailDTO, User loginUser) throws Exception
    {        
	    int result = maPtMstrVendorDetailDAO.insertPtVendor( maPtMstrVendorDetailDTO, loginUser);
	    // ������ ������ ������Ʈ �Ѵ�. 
	    maPtMstrVendorDetailDAO.updateVendor(maPtMstrVendorDetailDTO, loginUser);
	    
        return result;
    }
}
