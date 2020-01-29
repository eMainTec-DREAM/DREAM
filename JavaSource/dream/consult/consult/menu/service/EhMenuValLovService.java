package dream.consult.consult.menu.service;

import java.util.List;

import common.bean.User;
import dream.consult.consult.menu.form.EhMenuValLovForm;
/**
 * 컨설트 메뉴 LOV - List Service
 * @author kim21017
 * @version $Id: EhMenuValLovService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since 1.0
 */
public interface EhMenuValLovService {
	/**
	 * FIND LIST
	 * @param ehMenuValLovForm
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public List findList(EhMenuValLovForm ehMenuValLovForm, User user) throws Exception;
}
