package dream.note.daily.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.note.daily.dao.MaWoDailyListDAO;
import dream.note.daily.dto.MaWoDailyCommonDTO;

/**
 * 일일작업 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maWoDailyListDAOTarget"
 * @spring.txbn id="maWoDailyListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoDailyListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoDailyListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDailyCommonDTO
     * @return List
     */
    public List findList(MaWoDailyCommonDTO maWoDailyCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                    ");
        query.append("        ''                                                   AS seqNo                                                                      ");
        query.append("        ,''                                                  AS isDelCheck                                                                 ");
        query.append("        ,a.wodaylist_id                                      AS woDayListNo                                                                ");
        query.append("        ,a.description                                       AS title                                                                      ");
        query.append("        ,(SELECT description                                                                                                               ");
        query.append("          FROM TADEPT                                                                                                                      ");
        query.append("          WHERE comp_no = a.comp_no                                                                                                        ");
        query.append("          AND dept_id = a.wo_dept)                           AS woDept                                                                     ");
        query.append("        ,CASE WHEN a.wo_date IS NULL THEN NULL                                                                                             ");
        query.append("                  ELSE SUBSTR(a.wo_date, 0, 4)||'-'||SUBSTR(a.wo_date, 5, 2)||'-'||SUBSTR(a.wo_date, 7, 2) END                   AS woDate ");
        query.append("        ,CASE WHEN a.start_fdate IS NULL THEN NULL                                                                                         ");
        query.append("                  ELSE SUBSTR(a.start_fdate, 1, 4)||'-'||SUBSTR(a.start_fdate, 5, 2)||'-'||SUBSTR(a.start_fdate, 7, 2) END||' '            ");
        query.append("         ||CASE WHEN a.start_ftime IS NULL THEN NULL                                                                                       ");
        query.append("                  ELSE SUBSTR(a.start_ftime, 1, 2)||':'||SUBSTR(a.start_ftime, 3, 2) END||' ~ '          ");
        query.append("         ||CASE WHEN a.start_edate IS NULL THEN NULL                                                                                       ");
        query.append("                  ELSE SUBSTR(a.start_edate, 1, 4)||'-'||SUBSTR(a.start_edate, 5, 2)||'-'||SUBSTR(a.start_edate, 7, 2) END||' '            ");
        query.append("         ||CASE WHEN a.start_etime IS NULL THEN NULL                                                                                       ");
        query.append("                  ELSE SUBSTR(a.start_etime, 1, 2)||':'||SUBSTR(a.start_etime, 3, 2) END       AS woTime ");
        query.append("        ,SFACODE_TO_DESC(a.wodaylist_status,'WODAYLIST_STATUS','SYS','','ko') wodaylistStatus                                              ");
        query.append("        ,(SELECT x.emp_name||'/'||y.description                                                                                            ");
        query.append("          FROM TAEMP x INNER JOIN TADEPT y                                                                                                 ");
        query.append("          ON x.dept_id=y.dept_id                                                                                                           ");
        query.append("          AND x.comp_no=y.comp_no                                                                                                          ");
        query.append("          WHERE x.emp_id=a.upd_by                                                                                                          ");
        query.append("          AND x.comp_no=a.comp_no)                           AS updBy                                                                      ");
        query.append("        ,(SELECT description                                                                                                               ");
        query.append("          FROM TAPLANT                                                                                                                     ");
        query.append("          WHERE plant=a.plant                                                                                                              ");
        query.append("          AND comp_no=a.comp_no)                             AS plant                                                                      ");
        query.append("        ,a.remark                                            AS remark                                                                     ");
        query.append("        ,a.wodaylist_id                                      AS woDayListId                                                                ");
        query.append("FROM   TAWODAYLIST a         ");
        query.append("WHERE 1=1     ");
        query.append(this.getWhere(maWoDailyCommonDTO));
        query.getOrderByQuery("a.wo_date DESC,wodaylist_id DESC", maWoDailyCommonDTO.getOrderBy(), maWoDailyCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoDailyCommonDTO.getIsLoadMaxCount(), maWoDailyCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoDailyCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaWoDailyCommonDTO maWoDailyCommonDTO)
    {        
        QueryBuffer query = new QueryBuffer();
      
        // CommonDTO의 equipNo가 있는경우는 상세에서 수정 되어서 List의 한 Row만을 재조회
        if (!"".equals(maWoDailyCommonDTO.getWoDayListId()))
        {
            query.getAndQuery("a.wodaylist_id", maWoDailyCommonDTO.getWoDayListId());
            return query.toString();
        }
        
        query.getAndQuery("a.comp_no", maWoDailyCommonDTO.getCompNo());
        query.getAndDateQuery("a.wo_date", maWoDailyCommonDTO.getStartDate(), maWoDailyCommonDTO.getEndDate());
        query.getCodeLikeQuery("a.wo_dept", "(SELECT description FROM TADEPT WHERE comp_no = a.comp_no AND dept_id = a.wo_dept)", maWoDailyCommonDTO.getDeptId(), maWoDailyCommonDTO.getDeptDesc());
        query.getLikeQuery("a.description", maWoDailyCommonDTO.getTitle());
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant)", maWoDailyCommonDTO.getPlantId(), maWoDailyCommonDTO.getPlantDesc());
        
        return query.toString();
    }


    public int deleteList(String id, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("DELETE FROM TAWODAYLIST           ");
        query.append("WHERE wodaylist_id     = "+id+"       ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;

    }
    
    public int deleteActList(String id, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("DELETE FROM TAWODAYACT           ");
        query.append("WHERE wodaylist_id     = "+id+"       ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;

    }

	@Override
	public String findTotalCount(MaWoDailyCommonDTO maWoDailyCommonDTO, User user) throws Exception {
		
		QueryBuffer query = new QueryBuffer();

        query.append("SELECT            							");
        query.append("       COUNT(1)                               ");
        query.append("FROM   TAWODAYLIST a                          ");
        query.append("WHERE 1=1                  					");
        query.append(this.getWhere(maWoDailyCommonDTO));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);

	}
    
}