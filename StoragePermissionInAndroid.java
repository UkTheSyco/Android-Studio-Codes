//    File sdCard = Environment.getExternalStorageDirectory();
//    dir = new File (sdCard.getAbsolutePath() + "/Movies/Movie Syco");

//Maintain the for your purpose

// add below two lines in AndroidManifest.xml
//<uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />
//<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />

public void checkPermissions(){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        if (Environment.isExternalStorageManager()) {
            Log.d(TAG,"Permission granted for users of android 11 or ABOVE");
        } else {
            // Permission is denied
            Log.d(TAG,"Asking for permission for users of android 11 or ABOVE");
            grantPermissions();
        }
    } else {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Permission is denied
            Log.d(TAG,"Asking for permission for BELOW android 11");
            grantPermissions();
        } else {
            // Permission is granted
            Log.d(TAG,"Permission granted for users BELOW android 11");
        }
    }
}
// Function to check and request permission.
public void grantPermissions(){
    ActivityResultLauncher<Intent> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            result -> {
                checkPermissions();
            });
    Toast.makeText(this, "Click on -> Allow access to manage all files and then came back to app", Toast.LENGTH_LONG).show();
    Toast.makeText(this, "Click on -> Allow access to manage all files and then came back to app", Toast.LENGTH_LONG).show();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        // Request permission to access all files on external storage
        if (!Environment.isExternalStorageManager()) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            requestPermissionLauncher.launch(intent);
        }
    } else {
        // Request permission to access external storage
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }
}

checkPermissions();