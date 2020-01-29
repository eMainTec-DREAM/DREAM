package dream.req.work.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.req.work.dao.ReqWorkResListDAO;
import dream.req.work.dto.ReqWorkCommonDTO;
import dream.req.work.dto.ReqWorkResListDTO;

/**
 * 작업요청-처리사항 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="reqWorkResListDAOTarget"
 * @spring.txbn id="reqWorkResListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqWorkResListDAOOraImpl extends BaseJdbcDaoSupportOra implements ReqWorkResListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkCommonDTO
     * @return List
     */
    public List findList(ReqWorkCommonDTO reqWorkCommonDTO,ReqWorkResListDTO reqWorkResListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

            query.append("SELECT												");
            query.append("        ''						seqNo,				");
            query.append("        ''						isDelCheck,			");
            query.append("        x.woreqres_id				woReqResId,			");
	        query.getDate("x.res_date", "resDate,");
	        query.append("		  (SELECT a.emp_name							");
	        query.append("			FROM TAEMP a								");
	        query.append("			WHERE a.comp_no = x.comp_no					");
	        query.append("			AND a.emp_id = x.emp_id						");
	        query.append("			)||'/'||									");
	        query.append("			(SELECT a.description						");
	        query.append("			FROM TADEPT a								");
	        query.append("			WHERE a.comp_no = x.comp_no					");
	        query.append("			AND a.dept_id = x.dept_id					");
	        query.append("			)						resBy,				");
	        query.append("			wkor_id					wkorId,				");
	        query.append("		  SFACODE_TO_DESC(x.wores_status,'WORES_STATUS','SYS','','"+user.getLangId()+"') woresStatusDesc,");
            query.append("        x.response				response,			");
            query.append("        x.invtlist_id				invtlistId			");
            query.append("        ,NVL((SELECT SFACODE_TO_DESC(invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"')                         ");
            query.append("         FROM   (SELECT invt_proc_ltype,                          ");
            query.append("                        invtlist_id,                              ");
            query.append("                        comp_no                                   ");
            query.append("                 FROM   TAINVTPHASE                           ");
            query.append("                 WHERE  1=1                                       ");
            query.append("                   AND  invtphase_status IN ('C','P')             ");
            query.append("                 ORDER BY ord_no DESC                             ");
            query.append("                 ) A                                              ");
            query.append("         WHERE ROWNUM = 1                                         ");
            query.append("           AND invtlist_id = x.invtlist_id                    ");
            query.append("           AND comp_no = x.comp_no                                ");
            query.append("        ) , (SELECT SFACODE_TO_DESC(y.wo_status,'WO_STATUS','SYS','','"+user.getLangId()+"')         ");
            query.append("             FROM  TAWORKORDER y                                  ");
            query.append("             WHERE y.wkor_id = x.wkor_id                          ");
            query.append("            )                                                     ");
            query.append("        ) actualStatus    ");
            query.append("FROM TAWOREQRES x 									");
            query.append("WHERE 1=1												");
            query.append(this.getWhere(reqWorkCommonDTO,reqWorkResListDTO,user));
            query.getOrderByQuery("x.woreqres_id desc", reqWorkResListDTO.getOrderBy(), reqWorkResListDTO.getDirection());
            
            return getJdbcTemplate().queryForList(query.toString(reqWorkResListDTO.getIsLoadMaxCount(), reqWorkResListDTO.getFirstRow()));

    }


    public int deleteList(String id, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("DELETE FROM TAWOREQRES               ");
        query.append("WHERE woreqres_id     = "+id+"       ");
        query.append("  AND comp_no  = '"+compNo+"'        ");

        rtnValue = this.getJdbcTemplate().update(query.toString());

        return rtnValue;

    }
    private String getWhere(ReqWorkCommonDTO reqWorkCommonDTO,ReqWorkResListDTO reqWorkResListDTO, User user){
    	QueryBuffer query = new QueryBuffer();

    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getAndNumKeyQuery("x.woreq_id", reqWorkCommonDTO.getWoReqId());

        if (!"".equals(reqWorkResListDTO.getWoReqResId()))
        {
            query.getAndQuery("x.woreqres_id", reqWorkResListDTO.getWoReqResId());
            return query.toString();
        }

        return query.toString();
    }


	@Override
	public String findTotalCount(ReqWorkCommonDTO reqWorkCommonDTO, ReqWorkResListDTO reqWorkResListDTO, User user)
			throws Exception {

		QueryBuffer query = new QueryBuffer();

        query.append("SELECT				");
        query.append("       COUNT(1)       ");
        query.append("FROM TAWOREQRES x		");
        query.append("WHERE 1=1				");
        query.append(this.getWhere(reqWorkCommonDTO,reqWorkResListDTO,user));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);

	}

}