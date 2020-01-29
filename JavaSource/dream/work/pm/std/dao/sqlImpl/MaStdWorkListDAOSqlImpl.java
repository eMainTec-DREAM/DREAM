package dream.work.pm.std.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QuerySqlBuffer;
import dream.work.pm.std.dao.MaStdWorkListDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWorkListDTO;

/**
 * 표준항목 리스트 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maStdWorkListDAOTarget"
 * @spring.txbn id="maStdWorkListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaStdWorkListDAOSqlImpl extends BaseJdbcDaoSupportOra implements MaStdWorkListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param maStdWorkListDTO
     * @param loginUser
     * @return List
     */
    public List findStdPointList(MaStdPointCommonDTO maStdPointCommonDTO, MaStdWorkListDTO maStdWorkListDTO, User loginUser)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 
       
        query.append("SELECT																	");
        query.append("		'' 							isDelCheck,								");
        query.append("		step_num				    stepNum,								");
        query.append("		dbo.SFACODE_TO_DESC((SELECT y.wo_type FROM TASTWRKWORK y WHERE x.stwrkwork_id=y.stwrkwork_id AND x.comp_no=y.comp_no),'WO_TYPE','SYS','','"+loginUser.getLangId()+"') woType,	");
        query.append("		x.description description,											");
        query.append("		x.remark remark,													");
        query.append("		x.stwrkworkprc_id stwrkWorkPrcId,									");
        query.append("		x.stwrk_id stwrkId,													");
        query.append("		x.stwrkwork_id stWrkWorkId											");
        query.append("FROM   TASTWRKWORKPRC x													");
        query.append("WHERE  1=1                            									"); 
        query.append(this.getWhere(maStdPointCommonDTO,maStdWorkListDTO,loginUser));
        query.append("ORDER by x.stwrkwork_id DESC												");
         
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param maStdWorkListDTO
     * @param user
     * @return
     * @throws Exception
     */
    private String getWhere(MaStdPointCommonDTO maStdPointCommonDTO,MaStdWorkListDTO maStdWorkListDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.stwrk_id", maStdPointCommonDTO.getStWrkId()); 
     // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maStdWorkListDTO.getStWrkWorkPrcId()))
        {
            query.getAndQuery("x.stwrkworkprc_id", maStdWorkListDTO.getStWrkWorkPrcId());
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("DELETE TASTWRKWORKPRC                             ");
        query.append("WHERE  comp_no         = ?                      ");
        query.append("  AND  stwrkworkprc_id  = ?                      ");

        Object[] objects = new Object[] {   
        		loginUser.getCompNo(),
                id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}
