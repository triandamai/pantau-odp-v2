package com.trian.module.data

import com.trian.domain.models.bean.MedicalRecordMethod
import com.trian.domain.models.bean.MedicalRecordType

val idMember = "ini_user_code_unique"
val measurements = listOf(
    MedicalRecord(
        id= 1,
        member_id=idMember,
        nurse_id="",
        device_id="ini device id",
        type=MedicalRecordType.TEMPERATURE,
        value="34",
        created_at=1632889813000,
        end_at=1632889813000,
        updated_at=1632889813000,
        is_upload=false,
        assets = "",
        diagnosis = "",
        method = MedicalRecordMethod.MANUAL,
        mime_type = "",
        note = "",
        title = ""
    ),
    MedicalRecord(
        id= 2,
        member_id=idMember,
        nurse_id="",
        device_id="ini device id",
        type=MedicalRecordType.RESPIRATION,
        value="21",
        created_at=1632889813000,
        end_at=1632889813000,
        updated_at=1632889813000,
        is_upload=false,
        assets = "",
        diagnosis = "",
        method = MedicalRecordMethod.MANUAL,
        mime_type = "",
        note = "",
        title = ""
    ),
    MedicalRecord(
        id= 3,
        member_id=idMember,
        nurse_id="",
        device_id="ini device id",
        type=MedicalRecordType.BLOOD_OXYGEN,
        value="90",
        created_at=1632889813000,
        end_at=1632889813000,
        updated_at=1632889813000,
        is_upload=false,
        assets = "",
        diagnosis = "",
        method = MedicalRecordMethod.MANUAL,
        mime_type = "",
        note = "",
        title = ""
    ),
    MedicalRecord(
        id= 4,
        member_id=idMember,
        nurse_id="",
        device_id="ini device id",
        type=MedicalRecordType.BLOOD_PRESSURE,
        value="122/78",
        created_at=1632889813000,
        end_at=1632889813000,
        updated_at=1632889813000,
        is_upload=false,
        assets = "",
        diagnosis = "",
        method = MedicalRecordMethod.MANUAL,
        mime_type = "",
        note = "",
        title = ""
    ),
)