package dream.work.pm.std.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QuerySqlBuffer;
import dream.work.pm.std.dao.MaStdWoTypeListDAO;
import dream.work.pm.std.dto.MaStdPointCommonDTO;
import dream.work.pm.std.dto.MaStdWoTypeListDTO;

/**
 * 표준항목 리스트 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maStdWoTypeListDAOTarget"
 * @spring.txbn id="maStdWoTypeListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaStdWoTypeListDAOSqlImpl extends BaseJdbcDaoSupportOra implements MaStdWoTypeListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param maStdWoTypeListDTO
     * @param loginUser
     * @return List
     */
    public List findStdPointList(MaStdPointCommonDTO maStdPointCommonDTO, MaStdWoTypeListDTO maStdWoTypeListDTO, User loginUser)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer(); 
       
        query.append("SELECT									");
        query.append("		''	 					seqNo		");
        query.append("		, '' 					isDelCheck	");
        query.append("		, (SELECT dbo.SFACODE_TO_DESC(x.wo_type,'WO_TYPE','SYS','','"+loginUser.getLangId()+"') ) woType		");
        query.append("		, (SELECT dbo.SFACODE_TO_DESC(x.pm_type, x.wo_type+'_TYPE','SYS','','"+loginUser.getLangId()+"') ) pmType		");
        query.append("		, x.remark 				remark		");
        query.append("		, x.stwrkwork_id		stwrkWorkId	");
        query.append("		, x.stwrk_id			stwrkId		");
        query.append("		, x.description 		description ");
        query.append("FROM   TASTWRKWORK x						");
        query.append("WHERE  1=1                            	"); 
        query.append(this.getWhere(maStdPointCommonDTO,maStdWoTypeListDTO,loginUser));
        query.append("ORDER by x.stwrkwork_id DESC				");
         
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maStdPointCommonDTO
     * @param maStdWoTypeListDTO
     * @param user
     * @return
     * @throws Exception
     */
    private String getWhere(MaStdPointCommonDTO maStdPointCommonDTO,MaStdWoTypeListDTO maStdWoTypeListDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.stwrk_id", maStdPointCommonDTO.getStWrkId()); 
     // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maStdWoTypeListDTO.getStWrkWorkId()))
        {
            query.getAndQuery("x.stwrkwork_id", maStdWoTypeListDTO.getStWrkWorkId());
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
        
        query.append("DELETE TASTWRKWORK                             ");
        query.append("WHERE  comp_no         = ?                      ");
        query.append("  AND  stwrkwork_id  = ?                      ");

        Object[] objects = new Object[] {   
        		loginUser.getCompNo(),
                id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}