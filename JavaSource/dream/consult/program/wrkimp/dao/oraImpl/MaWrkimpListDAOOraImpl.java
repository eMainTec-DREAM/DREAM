package dream.consult.program.wrkimp.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.program.wrkimp.dao.MaWrkimpListDAO;
import dream.consult.program.wrkimp.dto.MaWrkimpCommonDTO;

/**
 * 사원 - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maWrkimpListDAOTarget"
 * @spring.txbn id="maWrkimpListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWrkimpListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWrkimpListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWrkimpCommonDTO
     * @return List
     */
    public List findWrkimpList(MaWrkimpCommonDTO maWrkimpCommonDTO, User user)
    { 
        QueryBuffer query = new QueryBuffer(); 

        query.append("SELECT                                                                                                ");
        query.append("       '' seqNo                                                                                       ");
        query.append("       ,'' isDelCheck                                                                                 ");
        query.append("       ,x.gowrkimp_no as   gowrkimpNo                                                                 ");
        query.append("       ,x.description as   description                                                                ");
        query.append("       ,SFACODE_TO_DESC(x.gowrkimp_status,'GOWRKIMP_STATUS','SYS','','"+user.getLangId()+"') AS gowrkimpStatus          ");
        query.append("       ,x.write_name  as   writeBy                                                                    ");
        query.append("       ,x.write_date  as   writeDate                                                                  ");
        query.append("       ,x.view_name   as   viewBy                                                                     ");
        query.append("       ,x.view_date   as   viewDate                                                                   ");
        query.append("       ,x.work_sdate  as   workSdate                                                                  ");
        query.append("       ,x.work_edate  as   workEdate                                                                  ");
        query.append("       ,x.work_name   as   workBy                                                                     ");
        query.append("       ,y.helpdesk_no as   helpdeskNo                                                                 ");
        query.append("       ,SFACODE_TO_DESC(y.helpdesk_status,'HELPDESK_STATUS','SYS','','"+user.getLangId()+"') as helpdeskStatus          ");
        query.append("       ,(SELECT emp_name                                                                              ");
        query.append("         FROM   TAEMP                                                                                 ");
        query.append("         WHERE comp_no = y.comp_no                                                                    ");
        query.append("            AND emp_id = y.req_by)    as reqBy                                                        ");
        query.append("       ,y.req_date                    as reqDate                                                      ");
        query.append("       ,x.gowrkimp_id                 AS gowrkimpId                                                   ");
        query.append("       ,x.helpdesk_id                 AS helpdeskId                                                   ");
        
        query.append("       ,x.work_time                   as workTime                                                     ");
        query.append("       ,x.perform                     as perform                                                      ");
        query.append("       ,SFACODE_TO_DESC(x.wrkimp_cre_type,'WRKIMP_CRE_TYPE','SYS','','"+user.getLangId()+"') as wrkimpCreTypeDesc       ");
        query.append("       ,x.wrkimp_site                 as wrkimpSite                                                   ");

        
        query.append("FROM   TAGOWRKIMP x left outer join TAHELPDESK y on x.comp_no = y.comp_no and x.helpdesk_id = y.helpdesk_id       ");
        query.append("WHERE 1=1  ");
        query.append(this.getWhere(maWrkimpCommonDTO, user));
        query.getOrderByQuery("x.gowrkimp_no", maWrkimpCommonDTO.getOrderBy(), maWrkimpCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWrkimpCommonDTO.getIsLoadMaxCount(), maWrkimpCommonDTO.getFirstRow()));
        
    } 
    
    /**
     * Filter 조건
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWrkimpCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaWrkimpCommonDTO maWrkimpCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
	    if (!"".equals(maWrkimpCommonDTO.getGowrkimpId()))
	    {
	        query.getAndNumKeyQuery("x.gowrkimp_id", maWrkimpCommonDTO.getGowrkimpId());
	        return query.toString();
	    }

        query.getAndDateQuery("x.write_date", maWrkimpCommonDTO.getWorkSDate(), maWrkimpCommonDTO.getWorkEDate());
        query.getLikeQuery("x.description", maWrkimpCommonDTO.getDescription());
        query.getCodeLikeQuery("x.gowrkimp_status", "SFACODE_TO_DESC(x.gowrkimp_status,'GOWRKIMP_STATUS','SYS','','"+user.getLangId()+"')", maWrkimpCommonDTO.getGowrkimpStatus(), maWrkimpCommonDTO.getGowrkimpStatusDesc());
        query.getLikeQuery("x.write_name", maWrkimpCommonDTO.getWriteByName());
        query.getLikeQuery("x.wrkimp_site", maWrkimpCommonDTO.getWrkimpSite());
        query.getLikeQuery("x.view_name", maWrkimpCommonDTO.getViewByName());
        query.getLikeQuery("x.work_name", maWrkimpCommonDTO.getWorkByName());
        query.getCodeLikeQuery("x.wrkimp_cre_type", "SFACODE_TO_DESC(x.wrkimp_cre_type,'WRKIMP_CRE_TYPE','SYS','','"+user.getLangId()+"')", maWrkimpCommonDTO.getWrkImpCreType(), maWrkimpCommonDTO.getWrkImpCreTypeDesc());

        return query.toString();
        
    }

    /**
     * 삭제
     * @author ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param empId
     * @return
     */
    public int deleteWrkimp(String gowrkimp, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAGOWRKIMP                        ");
        query.append("WHERE  1=1                               ");
        query.append("  AND  gowrkimp_id   = ?                 ");

        Object[] objects = new Object[] {   
        		gowrkimp
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }

	public String findTotalCount(MaWrkimpCommonDTO maWrkimpCommonDTO, User user) {
		QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT                ");
        query.append("       COUNT(1)       ");
        query.append("FROM   TAGOWRKIMP x left outer join TAHELPDESK y on x.comp_no = y.comp_no and x.helpdesk_id = y.helpdesk_id       ");
        query.append("WHERE 1=1  ");
        query.append(this.getWhere(maWrkimpCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}