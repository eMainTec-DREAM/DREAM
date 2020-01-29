package dream.part.rec.service.spring;

import common.bean.User;
import dream.part.rec.dao.MaPtRecSerialDetailDAO;
import dream.part.rec.dto.MaPtRecSerialDetailDTO;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecSerialListDTO;
import dream.part.rec.service.MaPtRecSerialDetailService;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 구매입고item - 상세
 * @author kim2107
 * @version $Id: MaPtRecSerialDetailServiceImpl.java,v 1.0 2015/12/04 09:10:27 kim2107 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPtRecSerialDetailServiceTarget"
 * @spring.txbn id="maPtRecSerialDetailService"
 * @spring.property name="maPtRecSerialDetailDAO" ref="maPtRecSerialDetailDAO"
 */
public class MaPtRecSerialDetailServiceImpl implements MaPtRecSerialDetailService
{
    private MaPtRecSerialDetailDAO maPtRecSerialDetailDAO = null;
    
    public MaPtRecSerialDetailDAO getMaPtRecSerialDetailDAO() {
		return maPtRecSerialDetailDAO;
	}

	public void setMaPtRecSerialDetailDAO(MaPtRecSerialDetailDAO maPtRecSerialDetailDAO) {
		this.maPtRecSerialDetailDAO = maPtRecSerialDetailDAO;
	}

	public MaPtRecSerialDetailDTO findDetail(MaPtRecSerialListDTO maPtRecSerialListDTO, MaPtRecCommonDTO maPtRecCommonDTO)throws Exception
    {
        return maPtRecSerialDetailDAO.findDetail(maPtRecSerialListDTO, maPtRecCommonDTO);
    }
    
	public int updateDetail(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO, MaPtRecCommonDTO maPtRecCommonDTO) throws Exception
    {        
        return maPtRecSerialDetailDAO.updateDetail(maPtRecSerialDetailDTO, maPtRecCommonDTO);
    }
	public int insertDetail(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO, MaPtRecCommonDTO maPtRecCommonDTO) throws Exception
    {        
        return maPtRecSerialDetailDAO.insertDetail( maPtRecSerialDetailDTO, maPtRecCommonDTO);
    }
	public String validSerial(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO, MaPtRecCommonDTO maPtRecCommonDTO, User loginUser){
		return maPtRecSerialDetailDAO.validSerial(maPtRecSerialDetailDTO, maPtRecCommonDTO, loginUser);
	}
}
