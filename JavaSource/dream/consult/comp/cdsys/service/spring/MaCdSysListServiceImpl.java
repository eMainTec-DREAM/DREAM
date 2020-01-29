package dream.consult.comp.cdsys.service.spring;

import java.util.List;

import dream.consult.comp.cdsys.dao.MaCdSysListDAO;
import dream.consult.comp.cdsys.dto.MaCdSysCommonDTO;
import dream.consult.comp.cdsys.service.MaCdSysListService;

/**
 * 시스템코드 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaCdSysListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maCdSysListServiceTarget"
 * @spring.txbn id="maCdSysListService"
 * @spring.property name="maCdSysListDAO" ref="maCdSysListDAO"
 */
public class MaCdSysListServiceImpl implements MaCdSysListService
{
    private MaCdSysListDAO maCdSysListDAO = null;

    public MaCdSysListDAO getMaCdSysListDAO() {
		return maCdSysListDAO;
	}

	public void setMaCdSysListDAO(MaCdSysListDAO maCdSysListDAO) {
		this.maCdSysListDAO = maCdSysListDAO;
	}

	public List findListTypeList(MaCdSysCommonDTO maCdSysCommonDTO)
    {      
        return maCdSysListDAO.findListTypeList(maCdSysCommonDTO);
    }
	
	public int deleteListType(String[] deleteRows) throws Exception{
        int result = 0;
//        MaCdSysDTOList maCdSysDTOList = null;
//        // sheet의 row 수만큼 반복한다. 
//        for (Iterator it = maCdSysList.iterator(); it.hasNext();)
//        {
//        	maCdSysDTOList = (MaCdSysDTOList) it.next();
//            // 해당 row의 상태(입력, 수정, 삭제)를 판단하여 값을 조회한다.
//            switch (maCdSysDTOList.getDtStatus())
//            {
//                // 수정
//                case 'U':
//                	if("Y".equals(maCdSysDTOList.getIsDelCheck())){
//                		result = maCdSysListDAO.deleteListType(maCdSysDTOList);
//                	}
//                    break;
//                default:
//                    break;
//            }
//        }
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maCdSysListDAO.deleteListType(id);
            }
        
        return result;
    }
}

