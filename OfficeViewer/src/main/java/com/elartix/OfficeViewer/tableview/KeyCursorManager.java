package com.elartix.OfficeViewer.tableview;

import java.util.HashMap;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
 
/*
 * Helper class for managing cursors.
 * (c) 2012 Martin van Zuilekom (http://martin.cubeactive.com)
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Get a cursor with GetManagedQuery, this will create or return a Cursor by unique identifier
 * Make sure to call Clear in the onDestroy void in the activity, this will close all cursors and clear the internal list.
 */

public class KeyCursorManager {
    HashMap<String, Cursor> fCursors = new HashMap<String, Cursor>();

    Activity fActivity = null;

    Boolean fClearing = false;

    public KeyCursorManager(Activity aActivity) {
        fActivity = aActivity;
    }

    public void Clear() {
        // close any cursors we are managing.
        synchronized (fCursors) {
            fClearing = true;
            try
            {
                for (HashMap.Entry<String, Cursor> entry : fCursors.entrySet()) {
                    Cursor _Cursor = entry.getValue();
                    if (_Cursor != null) {
                        if (_Cursor.isClosed() == false){
                            _Cursor.close();
                        }
                    }
                }
                fCursors.clear();
            }
            finally
            {
                fClearing = false;
            }
        }
    }

    public Cursor GetManagedQuery(String aId, Uri aUri, String[] aProjection, String aSelection, String[] aSelectionArgs, String aSortOrder)
    {
        synchronized (fCursors) {
            if (fClearing == true)
            {
                throw new IllegalStateException("Call GetManagedQuery() while clearing");
            }
            Cursor _Result = null;
            if (fCursors.containsKey(aId) == false) {
                _Result = fActivity.getContentResolver().query(aUri, aProjection, aSelection, aSelectionArgs, aSortOrder);
                fCursors.put(aId, _Result);
                return _Result;
            } else {
                _Result = fCursors.get(aId);
                if (_Result.isClosed()){
                    //Drop cursor if closed, we will add a new cursor
                    fCursors.remove(aId);
                    _Result = fActivity.getContentResolver().query(aUri, aProjection, aSelection, aSelectionArgs, aSortOrder);
                    fCursors.put(aId, _Result);
                } else {
                    //Cursor is still available, requery the data
                    _Result.requery();
                }
                return _Result;
            }
        }
    }
}