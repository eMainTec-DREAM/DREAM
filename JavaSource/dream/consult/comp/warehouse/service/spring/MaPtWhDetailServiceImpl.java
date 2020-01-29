package dream.consult.comp.warehouse.service.spring;

import common.bean.User;
import common.util.CommonUtil;
import dream.consult.comp.warehouse.dao.MaPtWhDetailDAO;
import dream.consult.comp.warehouse.dto.MaPtWhCommonDTO;
import dream.consult.comp.warehouse.dto.MaPtWhDetailDTO;
import dream.consult.comp.warehouse.service.MaPtWhDetailService;
import dream.doc.img.dao.MaDocImgDetailDAO;
import dream.part.list.dto.MaPtMstrDetailDTO;

/**
 * 부품창고 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtWhDetailServiceTarget"
 * @spring.txbn id="maPtWhDetailService"
 * @spring.property name="maPtWhDetailDAO" ref="maPtWhDetailDAO"
 */
public class MaPtWhDetailServiceImpl implements MaPtWhDetailService
{
    private MaPtWhDetailDAO maPtWhDetailDAO = null;
    
    public MaPtWhDetailDAO getMaPtWhDetailDAO() 
    {
		return maPtWhDetailDAO;
	}

	public void setMaPtWhDetailDAO(MaPtWhDetailDAO maPtWhDetailDAO) 
	{
		this.maPtWhDetailDAO = maPtWhDetailDAO;
	}

	public MaPtWhDetailDTO findDetail(MaPtWhCommonDTO maPtWhCommonDTO,User user)throws Exception
    {
        MaPtWhDetailDTO maPtWhDetailDTO = maPtWhDetailDAO.findDetail(maPtWhCommonDTO, user);
        
        return maPtWhDetailDTO;
    }
	
	public int insertDetail(MaPtWhDetailDTO maPtWhDetailDTO, User loginUser) throws Exception
    {   	
	    int result = 0;
	    
	    result  = maPtWhDetailDAO.insertDetail(maPtWhDetailDTO);

        return result;
    }
	
	public int updateDetail(MaPtWhDetailDTO maPtWhDetailDTO, User loginUser) throws Exception
    {        
        return maPtWhDetailDAO.updateDetail(maPtWhDetailDTO);
    }
}
