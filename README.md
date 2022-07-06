# Pantau Odp
this is monorepo for mobile app of Buss Tracker mobile application

## Module

Name | Description      | design
------------ | ------------- | ----- |
[`app_user`](app_user/README.md) | Application for user|[`figma`](https://www.figma.com/file/Wk21cf0hjf0kzgvCJtLsVe/Bus-tracker) [`assets`]()|
[`app_driver`](app_driver/README.md)|Application for doctor| [`figma`]() [`assets`]()
[`buildSrc`](buildSrc/README.md) | Source of Dependencies |
[`data`](data/README.md) | Data layer (Local Database,Networking)|
[`component`](component/README.md) | Component|


# pantau-odp-v2
creating apps for odp version 2

## Feature
1. Dashboard statistik
2. Merubah Profil
3. Me reset password
4. Login
### Koordinator
1. Nambah Data Pemantau
2. Melihat Hasil Pemantauan  Per Desa

### Pemantau
1. Memantau Warga(Assesment)
2. Melihat Hasil Pemantauan di regionnya(di desa penugasan)



## USER
- desa
- id induk
- kecamatan
- level[PEMANTAU,KOORDINTAOR,DINKES,DINSOS,BUPATI]
- nama
- nip
- opd
- password

## ASSESMENT
- created_at
- updated_at
- demam_string
- demam_val
- ispa_string
- ispa_val
- jenis_kelamin_string
- jenis_kelamin_val
- jumlah
- penyakit_string
- penyakit_val
- riwayat_string
- riwayat_val
- sesak_nafas_string
- sesak_nafas_val
- butuhbantuan
- daerah_dikunjungi
- go_darah
- jenis_kelamin
- jenisbantuan
- jumlah bantuan
- kondisi
- surat_isolasi

- perilaku
- riwayat_perjalanan

## WARGA DIPANTAU
- agama
- alamat
- nama
- nik
- nippemantai
- nohp
- pekerjaan
- rt
- rw
- status
- umur
- updated_at
- kecamatan
- kelurahan
- nippemantai
- created_at
- updated_at
- tanggal_lahir
- tampat_lahir