package dream.work.close.check.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.close.check.dao.MgrWorkCloseCheckPointListDAO;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckPointListDTO;

/**
 * 표준항목 리스트 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="mgrWorkCloseCheckPointListDAOTarget"
 * @spring.txbn id="mgrWorkCloseCheckPointListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrWorkCloseCheckPointListDAOOraImpl extends BaseJdbcDaoSupportOra implements MgrWorkCloseCheckPointListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckCommonDTO
     * @param mgrWorkCloseCheckPointListDTO
     * @param loginUser
     * @return List
     */
    public List findStdPointList(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User loginUser)
    { 
        QueryBuffer query = new QueryBuffer(); 
       
        query.append("SELECT																	");
        query.append("		''	 						seqNo									");
        query.append("		, '' 						isDelCheck								");
        query.append("		, x.step_num 				stepNum									");
        query.append("		, x.check_point 			CHECKPOINT								");
        query.append("		, x.check_method 			checkMethod								");
        query.append("		, x.fit_basis 				fitBasis								");
        query.append("		, (SELECT SFACODE_TO_DESC(x.check_type,'CHECK_TYPE','SYS','','"+loginUser.getLangId()+"') FROM DUAL ) 					checkTypeDesc		");
        query.append("      , x.check_min || '/' || x.check_basis_val || '/' || x.check_max || '/' || x.uom      									minBasisMaxUom      ");
        query.append("      , (SELECT SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', x.comp_no, '"+loginUser.getLangId()+"') FROM DUAL) 	scheduleTypeDesc	");
        query.append("		, x.is_active 				isActive								");
        query.append("		, x.stwrk_point_id 			stWrkPointId							");
//        query.append("		, x.stwrk_point_no pmPointNo									");
        query.append("      , CASE x.schedule_type                                     			");
        query.append("        WHEN 'T' THEN x.CYCLE||x.period_type                     			");
        query.append("        ELSE TO_CHAR(x.USAGE, '999,999,999,999,999')             			");
        query.append("         END                      CYCLE									");
        query.append("		, TO_CHAR(x.work_time, '999,999,999,999,999')		predTime		");
        query.append("		, x.remark 					remark									");
        query.append("		, x.ord_no					ordNo									");
        query.append("FROM   TASTWRKPOINT x														");
        query.append("WHERE  1=1                            									"); 
        query.append(this.getWhere(mgrWorkCloseCheckCommonDTO,mgrWorkCloseCheckPointListDTO,loginUser));
        query.getOrderByQuery("x.stwrk_point_id", "x.ord_no", mgrWorkCloseCheckPointListDTO.getOrderBy(), mgrWorkCloseCheckPointListDTO.getDirection());
         
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param mgrWorkCloseCheckCommonDTO
     * @param mgrWorkCloseCheckPointListDTO
     * @param user
     * @return
     * @throws Exception
     */
    private String getWhere(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO,MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.stwrk_id", mgrWorkCloseCheckCommonDTO.getStwrkId()); 
        query.getAndQuery("x.is_deleted", "N");
        
     // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(mgrWorkCloseCheckPointListDTO.getStWrkPointId()))
        {
            query.getAndQuery("x.stwrk_point_id", mgrWorkCloseCheckPointListDTO.getStWrkPointId());
            return query.toString();
        }
        return query.toString();
    }
    
    /**
     * 표준항목 삭제
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param id
     * @param loginUser
     * @return
     */
    public int deleteList(String id, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TASTWRKPOINT set                         ");
        query.append("		    is_deleted   = ?		   			  ");
        query.append("WHERE  comp_no         = ?                      ");
        query.append("  AND  stwrk_point_id  = ?                      ");

        Object[] objects = new Object[] {   
        		"Y"
        		,loginUser.getCompNo()
                ,id
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    
    @Override
    public String findTotalCount(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, MgrWorkCloseCheckPointListDTO mgrWorkCloseCheckPointListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                ");
        query.append("       COUNT(1)       ");
        query.append("	FROM TASTWRKPOINT x	");
        query.append(" WHERE 1 = 1          "); 
        query.append(this.getWhere(mgrWorkCloseCheckCommonDTO, mgrWorkCloseCheckPointListDTO, loginUser));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
    }
}