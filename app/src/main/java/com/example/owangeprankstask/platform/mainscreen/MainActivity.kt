package com.example.owangeprankstask.platform.mainscreen

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import com.example.owangeprankstask.R
import com.example.owangeprankstask.core.data.ContactItem
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    private val viewModel: MainViewModel by inject()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            LoaderManager.getInstance(this).initLoader(CONTACTS_LOADER_ID, null, this)
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), 10)
        }
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        when (id) {
            CONTACTS_LOADER_ID -> {
                contactsLoader()
            }
            else -> {
                CursorLoader(applicationContext)
            }
        }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        //The framework will take care of closing the
        // old cursor once we return.
        //The framework will take care of closing the
        // old cursor once we return.
        val contacts: List<ContactItem> = contactsFromCursor(data)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {

    }

    private fun contactsLoader(): Loader<Cursor> {
        val contactsUri: Uri =
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI // The content URI of the phone contacts
        val projection = arrayOf( // The columns to return for each row
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.LOOKUP_KEY,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.HAS_PHONE_NUMBER,
            ContactsContract.CommonDataKinds.Phone.NUMBER
        )

        return CursorLoader(
            applicationContext,
            contactsUri,
            projection,
            null,
            null,
            null
        )
    }

    private fun contactsFromCursor(cursor: Cursor?): List<ContactItem> {
        val contacts: MutableList<ContactItem> = ArrayList()
        cursor?.let {
            if (cursor.count > 0) {
                cursor.moveToFirst()
                do {
                    if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                        val id =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
                        val name =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                        val phone =
                            cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        val number =
                            cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))
                        contacts.add(
                            ContactItem(
                                id,
                                name,
                                phone,
                                number
                            )
                        )
                    }
                } while (cursor.moveToNext())
            }
        }
        return contacts
    }

    companion object {
        private const val CONTACTS_LOADER_ID = 1
    }
}