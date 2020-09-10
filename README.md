# RPdfGenerator
A sample project to generate PDF file from data using itextpdf/itext7 library

Master branch has the latest code and compatible with Swift 5. Check other branches for various Swift versions

![Alt text](https://github.com/rheyansh/RPdfGenerator/blob/master/Screenshots/1.png)
![Alt text](https://github.com/rheyansh/RPdfGenerator/blob/master/Screenshots/2.png)
![Alt text](https://github.com/rheyansh/RPdfGenerator/blob/master/Screenshots/3.png)

# Important Notes
------------------------------------------------------------------
* Add WRITE_EXTERNAL_STORAGE permission in AndroidManifest.xml
------------------------------------------------------------------

    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

------------------------------------------------------------------
* Add File Provider in AndroidManifest.xml
------------------------------------------------------------------
<!-- File Provider -->
        <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="com.rheyansh.rpdfgenerator.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/provider_paths" />
        </provider>
------------------------------------------------------------------
* Add XML resource folder (see provider_paths.xml in example folder)
------------------------------------------------------------------
<?xml version="1.0" encoding="utf-8"?>
<paths>
    <external-path name="external_files" path="."/>
</paths>
------------------------------------------------------------------
 
# Author   

* [Raj Sharma](https://github.com/rheyansh)

## Communication

* If you **found a bug**, open an issue.
* If you **want to contribute**, submit a pull request.
