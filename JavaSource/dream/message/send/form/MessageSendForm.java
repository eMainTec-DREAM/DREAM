package dream.message.send.form;

import common.struts.BaseForm;
import dream.message.send.dto.MessageSendDTO;

/**
 * form
 * @author  
 * @version $Id: $
 * @since   1.0
 *
 * @struts.form name="messageSendForm"
 */
public class MessageSendForm extends BaseForm{
	
	MessageSendDTO messageSendDTO = new MessageSendDTO();

	public MessageSendDTO getMessageSendDTO() {
		return messageSendDTO;
	}

	public void setMessageSendDTO(MessageSendDTO messageSendDTO) {
		this.messageSendDTO = messageSendDTO;
	}

}
