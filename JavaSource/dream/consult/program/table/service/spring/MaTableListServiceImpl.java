package dream.consult.program.table.service.spring;

import java.util.List;

import common.bean.User;
import dream.consult.program.table.dao.MaTableListDAO;
import dream.consult.program.table.dto.MaTableCommonDTO;
import dream.consult.program.table.service.MaTableListService;

/**
 * 데이터 테이블 - 목록 serviceimpl
 * @author kim21017
 * @version $Id: MaTableListServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maTableListServiceTarget"
 * @spring.txbn id="maTableListService"
 * @spring.property name="maTableListDAO" ref="maTableListDAO"
 */
public class MaTableListServiceImpl implements MaTableListService
{
    private MaTableListDAO maTableListDAO = null;

    public MaTableListDAO getMaTableListDAO() {
		return maTableListDAO;
	}

	public void setMaTableListDAO(MaTableListDAO maTableListDAO) {
		this.maTableListDAO = maTableListDAO;
	}

	public List findListTypeList(MaTableCommonDTO maTableCommonDTO)
    {      
        return maTableListDAO.findListTypeList(maTableCommonDTO);
    }
	
	public int deleteListType(String[] deleteRows) throws Exception{
        int result = 0;
//        MaTableDTOList maTableDTOList = null;
//        // sheet의 row 수만큼 반복한다. 
//        for (Iterator it = maTableList.iterator(); it.hasNext();)
//        {
//        	maTableDTOList = (MaTableDTOList) it.next();
//            // 해당 row의 상태(입력, 수정, 삭제)를 판단하여 값을 조회한다.
//            switch (maTableDTOList.getDtStatus())
//            {
//                // 수정
//                case 'U':
//                	if("Y".equals(maTableDTOList.getIsDelCheck())){
//                		result = maTableListDAO.deleteListType(maTableDTOList);
//                	}
//                    break;
//                default:
//                    break;
//            }
//        }
        if(!deleteRows.equals(null))
            for(String id : deleteRows)
            {
                result = result + maTableListDAO.deleteListType(id);
            }
        
        return result;
    }

	@Override
	public String findTotalCount(MaTableCommonDTO maTableCommonDTO, User user) throws Exception {

		return maTableListDAO.findTotalCount(maTableCommonDTO, user);
	}
}

