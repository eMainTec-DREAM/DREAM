package dream.part.rec.service.spring;

import java.util.List;

import dream.part.rec.dao.MaPtRecSerialListDAO;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecSerialListDTO;
import dream.part.rec.service.MaPtRecSerialListService;

/**
 * 구매입고item 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaPtRecSerialListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPtRecSerialListServiceTarget"
 * @spring.txbn id="maPtRecSerialListService"
 * @spring.property name="maPtRecSerialListDAO" ref="maPtRecSerialListDAO"
 */
public class MaPtRecSerialListServiceImpl implements MaPtRecSerialListService
{
    private MaPtRecSerialListDAO maPtRecSerialListDAO = null;

    public MaPtRecSerialListDAO getMaPtRecSerialListDAO() {
		return maPtRecSerialListDAO;
	}
	public void setMaPtRecSerialListDAO(MaPtRecSerialListDAO maPtRecSerialListDAO) {
		this.maPtRecSerialListDAO = maPtRecSerialListDAO;
	}
	
	public List findItemList(MaPtRecCommonDTO maPtRecCommonDTO, MaPtRecSerialListDTO maPtRecSerialListDTO)
    {      
        return maPtRecSerialListDAO.findItemList(maPtRecCommonDTO, maPtRecSerialListDTO);
    }
	
	public int deleteItemList(String[] deleteRows , String[] deleteRowsExt) throws Exception{
        int result = 0;
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + maPtRecSerialListDAO.deleteItemList(deleteRows[i], deleteRowsExt[i] );
        }
        
        return result;
    }
}

