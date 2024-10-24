package ext.dgt.util;

import wt.fc.PersistenceHelper;
import wt.iba.value.AbstractValue;
import wt.iba.value.DefaultAttributeContainer;
import wt.iba.value.IBAHolder;
import wt.iba.value.IBAHolderReference;
import wt.iba.value.StringValue;
import wt.iba.value.litevalue.AbstractValueView;
import wt.iba.value.litevalue.StringValueDefaultView;
import wt.iba.value.service.IBAValueHelper;

public class IBAUtil {

	public static void setIBAValueStr( Object obj , String ibaname , String ibavalue ) throws Exception {
		IBAHolder ibaholder = (IBAHolder) obj;
		ibaholder = IBAValueHelper.service.refreshAttributeContainer(ibaholder , null , null , null);
		DefaultAttributeContainer defaultattributecontainer = (DefaultAttributeContainer) ibaholder.getAttributeContainer();
		System.out.println(defaultattributecontainer.toString()+"::::::::::::::ibaholder.getAttrContainer");
		AbstractValueView abstractValuesView[] = defaultattributecontainer.getAttributeValues();

		for (int k = 0; k < abstractValuesView.length; k++) {
			AbstractValue abstractvalue = (AbstractValue) PersistenceHelper.manager.refresh(abstractValuesView[k].getObjectID());
			System.out.println(abstractValuesView[k].toString()+"::::::::::abstractvalueview");
			System.out.println(abstractValuesView[k].getDefinition().getName()+":::::definitionName");
			if (abstractValuesView[k].getDefinition().getName().equals(ibaname)) {
				if (abstractValuesView[k] instanceof StringValueDefaultView) {
					System.out.println("ibaValue:::::::::::::::::"+ ibavalue);
					((StringValue) abstractvalue).setValue(ibavalue);
					((StringValue) abstractvalue).setIBAHolderReference(IBAHolderReference.newIBAHolderReference(ibaholder));
				}

				PersistenceHelper.manager.save(abstractvalue);
				break;
			}
		}
	}

	// 해당 object가 가진 IBA 속성들을 가져와 조회할 IBA 속성의 value를 반환하는 메소드
	public static String getIBAValueStr(Object obj,String ibaName) throws Exception {
		// IBA속성 저장 변수
		String value = "";
		try{
			IBAHolder ibaholder = (IBAHolder) obj;
			ibaholder = IBAValueHelper.service.refreshIBAHolder(ibaholder, null, null, null);
			// 해당 객체의 속성 컨테이너 가져옴
			DefaultAttributeContainer defaultattributecontainer = (DefaultAttributeContainer) ibaholder.getAttributeContainer();
			
			if(defaultattributecontainer != null) {
				// 객체가 가진 속성값 배열에 저장
				AbstractValueView[] abstractValuesView = defaultattributecontainer.getAttributeValues();
				
				for(int i = 0; i <  abstractValuesView.length; i++) {
					if (abstractValuesView[i].getDefinition().getName().equals(ibaName)) {
						// i번째 배열의 값이 String형이면 value에 속성값 저장
						if(abstractValuesView[i] instanceof StringValueDefaultView)
							value = ((StringValueDefaultView) abstractValuesView[i]).getValue();
					}
				}
			}
		}catch(Exception e){
			throw e;
		}
		return value;
	}

	
}
