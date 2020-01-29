package dream.consult.program.table.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.table.dao.MaTableColListDAO;
import dream.consult.program.table.dto.MaTableColListDTO;
import dream.consult.program.table.dto.MaTableCommonDTO;
import dream.consult.program.table.service.MaTableColListService;

/**
 * 데이터 테이블 detail-code 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaTableColListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maTableColListServiceTarget"
 * @spring.txbn id="maTableColListService"
 * @spring.property name="maTableColListDAO" ref="maTableColListDAO"
 */
public class MaTableColListServiceImpl implements MaTableColListService
{
    private MaTableColListDAO maTableColListDAO = null;


	public List findCodeList(MaTableCommonDTO maTableCommonDTO, MaTableColListDTO maTableColListDTO, String lang)
    {      
        return maTableColListDAO.findCodeList(maTableCommonDTO, maTableColListDTO, lang);
    }

	public MaTableColListDAO getMaTableColListDAO() {
		return maTableColListDAO;
	}


	public void setMaTableColListDAO(MaTableColListDAO maTableColListDAO) {
		this.maTableColListDAO = maTableColListDAO;
	}
	
	public int deleteCodeList(String[] deleteRows , String[] deleteRowsExt) throws Exception{
        int result = 0;
//        ListTypeCodeDTOList listTypeCodeDTOList = null;
//        // sheet의 row 수만큼 반복한다. 
//        for (Iterator it = listTypeCodeList.iterator(); it.hasNext();)
//        {
//        	listTypeCodeDTOList = (ListTypeCodeDTOList) it.next();
//            // 해당 row의 상태(입력, 수정, 삭제)를 판단하여 값을 조회한다.
//            switch (listTypeCodeDTOList.getDtStatus())
//            {
//                // 수정
//                case 'U':
//                	if("Y".equals(listTypeCodeDTOList.getIsDelCheck())){
//                		result = maTableColListDAO.deleteCodeList(listTypeCodeDTOList);
//                	}
//                    break;
//                default:
//                    break;
//            }
//        }
        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + maTableColListDAO.deleteCodeList(deleteRows[i], deleteRowsExt[i] );
        }
        
        return result;
    }

	public String findTotalCount(MaTableCommonDTO maTableCommonDTO, MaTableColListDTO maTableColListDTO, User user) {
		return maTableColListDAO.findTotalCount(maTableCommonDTO, maTableColListDTO, user);
	}
}

