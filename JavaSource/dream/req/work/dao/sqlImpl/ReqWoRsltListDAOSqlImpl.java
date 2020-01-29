package dream.req.work.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.req.work.dao.ReqWoRsltListDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWoRsltListDTO;

/**
 * 작업요청-작업결과 - 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="reqWoRsltListDAOTarget"
 * @spring.txbn id="reqWoRsltListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqWoRsltListDAOSqlImpl extends BaseJdbcDaoSupportSql implements ReqWoRsltListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqCommonDTO
     * @return List
     */
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoRsltListDTO reqWoRsltListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT																					");
        query.append("		 ''																	AS seqNo		");
        query.append("	   , ''																	AS isDelCheck	");
        query.append("	   , x.woreqres_id 														AS woreqresId	");
        query.append("     , x.woreq_id 														AS woreqId		");
        query.append("     , y.wkor_id 															AS wkorId		");
        query.append("     , y.wo_no 															AS woNo			");
        query.append("     , y.description 														AS woDesc		");
        query.append("     , y.wkor_date 														AS woDate		");
        query.append("	   , (SELECT a.description																");
        query.append("			FROM TAEQUIPMENT a																");
        query.append("		   WHERE a.comp_no  = x.comp_no														");
        query.append("			 AND a.equip_id = z.equip_id) 									AS EQUIPDESC	");
        query.append("	   , (SELECT a.item_no																	");
        query.append("			FROM TAEQUIPMENT a																");
        query.append("		   WHERE a.comp_no  = x.comp_no														");
        query.append("			 AND a.equip_id = z.equip_id) 									AS EQUIPNO		");
        query.append("     , (SELECT a.description																");
        query.append("  	    FROM TADEPT a																	");
        query.append("  	   WHERE a.comp_no = y.comp_no														");
        query.append(" 		   	 AND a.dept_id = y.dept_id) 									AS deptDesc		");
        query.append("     , y.wo_type     														AS woType		");
        query.append("     , dbo.SFACODETODESC(y.wo_type, 'WO_TYPE', 'SYS', x.comp_no)			AS woTypeDesc	");
        query.append("     , y.pm_type     														AS pmType		");
        query.append("     , dbo.SFACODETODESC(y.pm_type, y.wo_type+'_TYPE', 'SYS', x.comp_no) 	AS pmTypeDesc	");
        query.append("     , dbo.SFACODETODESC(y.wo_status, 'WO_STATUS', 'SYS', x.comp_no)		AS woStatus		");
        query.append("     , y.perform 															AS remark		");
        query.append("     , (SELECT a.param1																	");
        query.append("    	    FROM TACDSYSD a																	");
        query.append("    	   WHERE a.list_Type = y.wo_type+'_TYPE'											");
        query.append("    		 AND a.cdsysd_no = y.pm_type) 									AS param		");
        query.append("	   , (SELECT a.full_desc 																");
        query.append("			FROM TAEQASMB a INNER JOIN TAWOEQUIP b											");
        query.append("                                  ON a.comp_no   = b.comp_no								");
        query.append("                                 AND a.eqasmb_id = b.eqasmb_id							");
        query.append("		   WHERE a.comp_no = y.comp_no														");
        query.append("			 AND b.wkor_id = y.wkor_id) 									AS EQASMBDESC	");
        query.append("	FROM TAWOREQRES x INNER JOIN TAWORKORDER y												");
        query.append("						 ON x.comp_no = y.comp_no											");
        query.append("						AND x.wkor_id = y.wkor_id											");
        query.append("					  INNER JOIN TAWOEQUIP z												");
        query.append("						 ON z.comp_no = y.comp_no											");
        query.append("						AND z.wkor_id = y.wkor_id											");
        query.append(" WHERE 1 = 1																				");
        query.append(this.getWhere(maWoReqCommonDTO, reqWoRsltListDTO, user));
        query.getOrderByQuery("x.woreqres_id", "y.wo_no", reqWoRsltListDTO.getOrderBy(), reqWoRsltListDTO.getDirection());
            
        return getJdbcTemplate().queryForList(query.toString(reqWoRsltListDTO.getIsLoadMaxCount(), reqWoRsltListDTO.getFirstRow()));
    }

    public int deleteList(String id, String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("DELETE FROM TAWOREQRES               ");
        query.append("WHERE woreqres_id     = "+id+"       ");
        query.append("  AND comp_no  = '"+compNo+"'        ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;

    }
    private String getWhere(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoRsltListDTO reqWoRsltListDTO, User user){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.woreqres_method", "WO");
    	
        query.getAndNumKeyQuery("x.woreq_id", maWoReqCommonDTO.getWoReqId());
        
        if (!"".equals(maWoReqCommonDTO.getWkorId()))
        {
            query.getAndQuery("x.wkor_id", maWoReqCommonDTO.getWkorId());
            return query.toString();
        }
        
        return query.toString();
    }


	@Override
	public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoRsltListDTO reqWoRsltListDTO, User user)
			throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT										");
        query.append("       COUNT(1)     							");
        query.append("FROM TAWOREQRES x INNER JOIN TAWORKORDER y	");
        query.append("ON x.comp_no = y.comp_no						");
        query.append("AND x.wkor_id = y.wkor_id						");
        query.append("WHERE 1=1										");
        query.append(this.getWhere(maWoReqCommonDTO,reqWoRsltListDTO,user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}
    
}